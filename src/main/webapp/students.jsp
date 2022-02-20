<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/common/head.html" %>
    <title>Students - Zaghloul Academy</title>
</head>
<body class="sidebar-mini" style="height: auto;">
<%@ include file="/WEB-INF/common/bodyStart.html" %>

<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Students List</h3>
                <div class="card-tools">
                    <button type="button" class="btn btn-block btn-outline-success" onclick="addStudent()">Add Student</button>
                </div>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 10%">Id</th>
                        <th style="width: 20%">Name</th>
                        <th style="width: 10%">Phone</th>
                        <th style="width: 40%">Address</th>
                        <th style="width: 10%">Edit</th>
                        <th style="width: 10%">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.studentList}" var="student">
                        <tr>
                            <td>${student.studentId}</td>
                            <td>${student.studentName}</td>
                            <td>${student.studentPhone}</td>
                            <td>${student.studentAddress}</td>
                            <td>
                                <button type="submit" class="btn btn-block btn-outline-warning" onclick="editStudent('${student.studentId}','${student.studentName}','${student.studentPhone}','${student.studentAddress}')">Edit</button>
                            </td>
                            <td>
                                <form method="post" action="student">
                                    <input type="hidden" name="studentId" value="${student.studentId}"/>
                                    <input type="hidden" name="method" value="delete"/>
                                    <button type="submit" class="btn btn-block btn-outline-danger"
                                            onclick="return confirm('Are you sure you want to delete this student?');">
                                        Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="addStudent" style="display: none;" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Add/Update Student</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <form id="saveClass" method="post" action="student">
                <div class="modal-body">
                    <input type="text" class="form-control" id="method" name="method" hidden>
                    <input type="text" class="form-control" id="studentId" name="studentId" hidden>
                    <div class="form-group">
                        <label for="studentName">Student Name</label>
                        <input type="text" class="form-control" id="studentName" placeholder="Enter student name" name="studentName" required>
                    </div>
                    <div class="form-group">
                        <label for="studentPhone">Student Phone</label>
                        <input type="text" class="form-control" id="studentPhone" placeholder="Enter student phone" name="studentPhone" required>
                    </div>
                    <div class="form-group">
                        <label for="studentAddress">Student Address</label>
                        <input type="text" class="form-control" id="studentAddress" placeholder="Enter student address" name="studentAddress" required>
                    </div>
                </div>
                <div class="modal-footer justify-content-between">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/common/bodyEnd.html" %>
<script>
    function addStudent(){
        $('#method').val('none');
        $('#studentId').val('');
        $('#studentName').val('');
        $('#studentPhone').val('');
        $('#studentAddress').val('');
        $('#addStudent').modal("show");
    }
    function editStudent(id,name,phone,address){
        $('#method').val('put');
        $('#studentId').val(id);
        $('#studentName').val(name);
        $('#studentPhone').val(phone);
        $('#studentAddress').val(address);
        $('#addStudent').modal("show");
    }
</script>
</div>
<%@ include file="/WEB-INF/common/scripts.html" %>
</body>
</html>
