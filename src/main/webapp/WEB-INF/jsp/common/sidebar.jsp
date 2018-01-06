<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="sidebar">
    <!-- /.search form -->
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu">
        <li class="treeview">
            <a href="#"><i class="fa fa-circle-o text-aqua"></i><span>导航1</span> </a>
        </li>
        <li class="treeview">
            <a href="#"><i class="fa fa-circle-o text-aqua"></i><span>导航2</span> </a>
        </li>
        <li class="treeview">
            <a href="#"><i class="fa fa-circle-o text-aqua"></i><span>导航3</span> </a>
        </li>
        <li class="treeview">
            <a href="#"><i class="fa fa-circle-o text-aqua"></i><span>用户管理</span><i
                    class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu" style="padding-left:20px">
                <li><a href="#">用户管理</a></li>
                <li><a href="/role/list">角色管理</a></li>
            </ul>
        </li>
        <li class="treeview">
            <a href="#"><i class="fa fa-circle-o text-aqua"></i><span>个人中心</span><i
                    class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu" style="padding-left:20px">
                <li><a href="/member/editMyInfo" >修改个人资料</a></li>
                <li><a href="/member/editMyPassword" >修改密码</a></li>
            </ul>
        </li>
    </ul>
</section>
