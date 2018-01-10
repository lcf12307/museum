<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.crtvu.Authority" %>
<%@ page import="com.crtvu.auth.AuthInterceptor" %>
<%@ page import="java.util.HashSet" %>
<%
    HashSet<String> authority=null;
    try{
        //获取权限
        Object object = session.getAttribute(AuthInterceptor.SESSION_AUTHS);
        if(object==null) {
            response.sendRedirect("/index");
            return;
        }
        authority = (HashSet<String>)object;
        if(authority==null){
            response.sendRedirect("/index");
            return;
        }
        if(authority.contains(Authority.Member.toString())){
            System.out.println("有member权限");
        }
    }catch (Exception e){
        e.printStackTrace();
        response.sendRedirect("/index");
    }
%>
<section class="sidebar">
    <!-- /.search form -->
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu">
        <%
        if(authority!=null&&authority.contains(Authority.Quota.toString())){
        %>
        <li class="treeview">
            <a href="/museum/quota/list"><i class="fa fa-circle-o text-aqua"></i><span>专家管理</span> </a>
        </li>
        <%
        }
        if(authority!=null&&authority.contains(Authority.Museum.toString())){
        %>
        <li class="treeview">
            <a href="/museum/museum/list/1"><i class="fa fa-circle-o text-aqua"></i><span>博物馆管理</span> </a>
        </li>
        <%
            }
            if(authority!=null&&authority.contains(Authority.NotificationForm.toString())){
        %>
        <li class="treeview">
            <a href="/notification/list"><i class="fa fa-circle-o text-aqua"></i><span>申报书管理</span> </a>
        </li>
        <%
            }
            if(authority!=null&&authority.contains(Authority.ScoreTable.toString())){
        %>
        <li class="treeview">
            <a href="/score/list"><i class="fa fa-circle-o text-aqua"></i><span>专家打分表管理</span> </a>
        </li>
        <%
            }
            if(authority!=null&&authority.contains(Authority.Calculate.toString())){
        %>
        <li class="treeview">
            <a href="/point/index"><i class="fa fa-circle-o text-aqua"></i><span>计算得分</span> </a>
        </li>
        <%
            }
            if(authority!=null&&authority.contains(Authority.Rank.toString())){
        %>
        <li class="treeview">
            <a href="#"><i class="fa fa-circle-o text-aqua"></i><span>查看排名</span><i
                    class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu" style="padding-left:20px">
                <li><a href="/score/listDingXingRank/2018">查看定性排名</a></li>
                <li><a href="/point/quantitativelist">查看定量排名</a></li>
                <li><a href="/point/totallist">查看总分排名</a></li>
            </ul>
        </li>
        <%
            }
            if(authority!=null&&authority.contains(Authority.StatisticsTable.toString())){
        %>
        <li class="treeview">
            <a href="#"><i class="fa fa-circle-o text-aqua"></i><span>统计表</span><i
                    class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu" style="padding-left:20px">
                <li><a href="/score/total/2018">总分统计表</a></li>
                <li><a href="/point/quantitative">定量评估结果统计表</a></li>
                <li><a href="/score/listDingXingScore/2018">定性评估结果统计表</a></li>
            </ul>
        </li>
        <%
            }
            if(authority!=null&&authority.contains(Authority.Member.toString())){
        %>
        <li class="treeview">
            <a href="#"><i class="fa fa-circle-o text-aqua"></i><span>用户管理</span><i
                    class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu" style="padding-left:20px">
                <li><a href="/member/list">用户管理</a></li>
                <li><a href="/role/list">角色管理</a></li>
            </ul>
        </li>
        <%
            }
            if(authority!=null){
        %>
        <li class="treeview">
            <a href="#"><i class="fa fa-circle-o text-aqua"></i><span>个人中心</span><i
                    class="fa fa-angle-left pull-right"></i></a>
            <ul class="treeview-menu" style="padding-left:20px">
                <li><a href="/member/editMyInfo" >修改个人资料</a></li>
                <li><a href="/member/editMyPassword" >修改密码</a></li>
            </ul>
        </li>
        <%}%>
    </ul>
</section>
