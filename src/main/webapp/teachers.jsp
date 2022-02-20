<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/common/head.html" %>
    <title>Teachers - Zaghloul Academy</title>
</head>
<body class="sidebar-mini" style="height: auto;">
<%@ include file="/WEB-INF/common/bodyStart.html" %>

<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Teachers List</h3>
                <div class="card-tools">
                    <button type="button" class="btn btn-block btn-outline-success" onclick="addTeacher()">Add Teacher</button>
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
                    <c:forEach items="${requestScope.teacherList}" var="teacher">
                        <tr>
                            <td>${teacher.teacherId}</td>
                            <td>${teacher.teacherName}</td>
                            <td>${teacher.teacherPhone}</td>
                            <td>${teacher.teacherAddress}</td>
                            <td>
                                <button type="submit" class="btn btn-block btn-outline-warning" onclick="editTeacher('${teacher.teacherId}','${teacher.teacherName}','${teacher.teacherPhone}','${teacher.teacherAddress}')">Edit</button>
                            </td>
                            <td>
                                <form method="post" action="teacher">
                                    <input type="hidden" name="teacherId" value="${teacher.teacherId}"/>
                                    <input type="hidden" name="method" value="delete"/>
                                    <button type="submit" class="btn btn-block btn-outline-danger"
                                            onclick="return confirm('Are you sure you want to delete this teacher?');">
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
<div class="modal fade" id="addTeacher" style="display: none;" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Add/Update Teacher</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <form id="saveClass" method="post" action="teacher">
                <div class="modal-body">
                    <input type="text" class="form-control" id="method" name="method" hidden>
                    <input type="text" class="form-control" id="teacherId" name="teacherId" hidden>
                    <div class="form-group">
                        <label for="teacherName">Teacher Name</label>
                        <input type="text" class="form-control" id="teacherName" placeholder="Enter teacher name" name="teacherName" required>
                    </div>
                    <div class="form-group">
                        <label for="teacherPhone">Teacher Phone</label>
                        <input type="text" class="form-control" id="teacherPhone" placeholder="Enter teacher phone" name="teacherPhone" required>
                    </div>
                    <div class="form-group">
                        <label for="teacherAddress">Teacher Address</label>
                        <input type="text" class="form-control" id="teacherAddress" placeholder="Enter teacher address" name="teacherAddress" required>
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
    function addTeacher(){
        $('#method').val('none');
        $('#teacherId').val('');
        $('#teacherName').val('');
        $('#teacherPhone').val('');
        $('#teacherAddress').val('');
        $('#addTeacher').modal("show");
    }
    function editTeacher(id,name,phone,address){
        $('#method').val('put');
        $('#teacherId').val(id);
        $('#teacherName').val(name);
        $('#teacherPhone').val(phone);
        $('#teacherAddress').val(address);
        $('#addTeacher').modal("show");
    }
</script>
</div>
<%@ include file="/WEB-INF/common/scripts.html" %>
</body>
</html>
