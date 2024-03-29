/**
 * Copyright (C), 2015-2021, 东北证券股份有限公司
 * FileName: IapHttpRequestRetryHandler
 * Author:   summer
 * Date:     2021/11/26 13:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.httpclient;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName IapHttpRequestRetryHandler
 * @Description
 * @Author summer
 * @Date 2021/11/26 13:20
 **/
public class IapHttpRequestRetryHandler implements HttpRequestRetryHandler
{
    /**
     * the number of times a method will be retried
     */
    private final int retryCount;

    /**
     * Whether or not methods that have successfully sent their request will be retried
     */
    private final boolean requestSentRetryEnabled;

    private final Set<Class<? extends IOException>> nonRetriableClasses;

    /**
     * Create the request retry handler using the specified IOException classes
     *
     * @param retryCount              how many times to retry; 0 means no retries
     * @param requestSentRetryEnabled true if it's OK to retry requests that have been sent
     * @param clazzes                 the IOException types that should not be retried
     * @since 4.3
     */
    protected IapHttpRequestRetryHandler(final int retryCount, final boolean requestSentRetryEnabled, final Collection<Class<? extends IOException>> clazzes)
    {
        super();
        this.retryCount = retryCount;
        this.requestSentRetryEnabled = requestSentRetryEnabled;
        this.nonRetriableClasses = new HashSet<Class<? extends IOException>>();
        for (final Class<? extends IOException> clazz : clazzes)
        {
            this.nonRetriableClasses.add(clazz);
        }
    }

    /**
     * Create the request retry handler using the following list of
     * non-retriable IOException classes: <br>
     * <ul>
     * <li>InterruptedIOException</li>
     * <li>UnknownHostException</li>
     * <li>ConnectException</li>
     * <li>SSLException</li>
     * </ul>
     *
     * @param retryCount              how many times to retry; 0 means no retries
     * @param requestSentRetryEnabled true if it's OK to retry requests that have been sent
     */
    @SuppressWarnings("unchecked")
    public IapHttpRequestRetryHandler(final int retryCount, final boolean requestSentRetryEnabled)
    {
        this(retryCount, requestSentRetryEnabled, Arrays.asList(InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class));
    }

    /**
     * Create the request retry handler with a retry count of 3, requestSentRetryEnabled false
     * and using the following list of non-retriable IOException classes: <br>
     * <ul>
     * <li>InterruptedIOException</li>
     * <li>UnknownHostException</li>
     * <li>ConnectException</li>
     * <li>SSLException</li>
     * </ul>
     */
    public IapHttpRequestRetryHandler()
    {
        this(3, false);
    }

    /**
     * Used <code>retryCount</code> and <code>requestSentRetryEnabled</code> to determine
     * if the given method should be retried.
     */
    public boolean retryRequest(final IOException exception, final int executionCount, final HttpContext context)
    {
        Args.notNull(exception, "Exception parameter");
        Args.notNull(context, "HTTP context");
        if (executionCount > this.retryCount)
        {
            // Do not retry if over max retry count
            return false;
        }
        if (this.nonRetriableClasses.contains(exception.getClass()))
        {
            return false;
        }
        else
        {
            for (final Class<? extends IOException> rejectException : this.nonRetriableClasses)
            {
                if (rejectException.isInstance(exception))
                {
                    if (exception instanceof SocketTimeoutException)
                    {
                        return true;
                    }
                    return false;
                }
            }
        }
        final HttpClientContext clientContext = HttpClientContext.adapt(context);
        final HttpRequest request = clientContext.getRequest();

        if (requestIsAborted(request))
        {
            return false;
        }

        if (handleAsIdempotent(request))
        {
            // Retry if the request is considered idempotent
            return true;
        }

        if (!clientContext.isRequestSent() || this.requestSentRetryEnabled)
        {
            // Retry if the request has not been sent fully or
            // if it's OK to retry methods that have been sent
            return true;
        }
        // otherwise do not retry
        return false;
    }

    /**
     * @return <code>true</code> if this handler will retry methods that have
     * successfully sent their request, <code>false</code> otherwise
     */
    public boolean isRequestSentRetryEnabled()
    {
        return requestSentRetryEnabled;
    }

    /**
     * @return the maximum number of times a method will be retried
     */
    public int getRetryCount()
    {
        return retryCount;
    }

    /**
     * @since 4.2
     */
    protected boolean handleAsIdempotent(final HttpRequest request)
    {
        return !(request instanceof HttpEntityEnclosingRequest);
    }

    /**
     * @since 4.2
     * @deprecated (4.3)
     */
    @Deprecated
    protected boolean requestIsAborted(final HttpRequest request)
    {
        HttpRequest req = request;
        if (request instanceof RequestWrapper)
        { // does not forward request to original
            req = ((RequestWrapper) request).getOriginal();
        }
        return (req instanceof HttpUriRequest && ((HttpUriRequest) req).isAborted());
    }
}
