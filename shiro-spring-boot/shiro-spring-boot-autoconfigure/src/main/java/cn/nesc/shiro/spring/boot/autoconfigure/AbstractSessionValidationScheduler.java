package cn.nesc.shiro.spring.boot.autoconfigure;

import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AbstractSessionValidationScheduler
 * @Description
 * @Author summer
 * @Date 2023/11/29 15:48
 **/
public abstract class AbstractSessionValidationScheduler implements SessionValidationScheduler, Runnable
{
    private static final Logger log = LoggerFactory.getLogger(AbstractSessionValidationScheduler.class);

    protected ValidatingSessionManager sessionManager;

    protected ScheduledExecutorService service;

    protected long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;

    protected boolean enabled = false;

    public abstract void run();

    public void enableSessionValidation()
    {
        log.debug("------- enableSessionValidation -------");
        if (this.interval > 0l)
        {
            this.service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory()
            {
                public Thread newThread(Runnable r)
                {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    return thread;
                }
            });
            this.service.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
            this.enabled = true;
        }
    }


    public ValidatingSessionManager getSessionManager()
    {
        return sessionManager;
    }

    public void setSessionManager(ValidatingSessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }

    public ScheduledExecutorService getService()
    {
        return service;
    }

    public void setService(ScheduledExecutorService service)
    {
        this.service = service;
    }

    public long getInterval()
    {
        return interval;
    }

    public void setInterval(long interval)
    {
        this.interval = interval;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}
