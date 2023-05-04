/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: UserListResult
 * Author:   summer
 * Date:     2022/9/5 15:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.itruscloud.result;

import cn.nesc.itruscloud.bean.User;

/**
 * @ClassName UserListResult
 * @Description 查询用户列表输出参数
 * @Author summer
 * @Date 2022/9/5 15:18
 **/
public class UserListResult extends ItrusBaseResult
{
    private ListResult<User> users;

    public ListResult<User> getUsers()
    {
        return users;
    }

    public void setUsers(ListResult<User> users)
    {
        this.users = users;
    }
}
