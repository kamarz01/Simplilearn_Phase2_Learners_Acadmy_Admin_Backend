<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/common/head.html" %>
    <title>Class Report - Zaghloul Academy</title>
</head>
<body class="sidebar-mini" style="min-height: 1000px;">
<%@ include file="/WEB-INF/common/bodyStart.html" %>
<c:choose>
<c:when test="${requestScope.classList.size() > 0}">
    <c:forEach items="${requestScope.classList}" var="_class">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Class Name: <strong>${_class.className}</strong></h3></br>
                        <h3 class="card-title">Class Year: <strong>${_class.classYear}</strong></h3>
                    </div>
                    <div class="card-body">
                        <!-- Student List-->
                        <p>Students</p>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 40%">Name</th>
                                <th style="width: 40%">Address</th>
                                <th style="width: 20%">Phone</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${_class.students}" var="student">
                                <tr>
                                    <td>${student.studentName}</td>
                                    <td>${student.studentAddress}</td>
                                    <td>${student.studentPhone}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </br>

                        <!-- Teachers List-->
                        <p>Teachers</p>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 40%">Name</th>
                                <th style="width: 40%">Address</th>
                                <th style="width: 20%">Phone</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${_class.teachers}" var="teacher">
                                <tr>
                                    <td>${teacher.teacherName}</td>
                                    <td>${teacher.teacherAddress}</td>
                                    <td>${teacher.teacherPhone}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </br>

                        <!-- Subjects List-->
                        <p>Subjects</p>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 20%">Code</th>
                                <th style="width: 40%">Name</th>
                                <th style="width: 40%">Description</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${_class.subjects}" var="subject">
                                <tr>
                                    <td>${subject.subjectCode}</td>
                                    <td>${subject.subjectName}</td>
                                    <td>${subject.subjectDescription}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</c:when>
<c:otherwise>
<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Class Report</h3>
            </div>
            <div class="card-body">
                <p>No Data to show ...</p>
            </div>
        </div>
    </div>
    </c:otherwise>
    </c:choose>


    <%@ include file="/WEB-INF/common/bodyEnd.html" %>
</div>
<%@ include file="/WEB-INF/common/scripts.html" %>
</body>
</html>
