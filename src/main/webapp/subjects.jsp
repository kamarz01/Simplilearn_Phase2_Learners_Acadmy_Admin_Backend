<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/common/head.html" %>
    <title>Subjects - Zaghloul Academy</title>
</head>
<body class="sidebar-mini" style="height: auto;">
<%@ include file="/WEB-INF/common/bodyStart.html" %>

<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Subjects List</h3>
                <div class="card-tools">
                    <button type="button" class="btn btn-block btn-outline-success" onclick="addSubject()">Add Subject</button>
                </div>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 10%">Id</th>
                        <th style="width: 20%">Name</th>
                        <th style="width: 10%">Code</th>
                        <th style="width: 40%">Description</th>
                        <th style="width: 10%">Edit</th>
                        <th style="width: 10%">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.subjectList}" var="subject">
                        <tr>
                            <td>${subject.subjectId}</td>
                            <td>${subject.subjectName}</td>
                            <td>${subject.subjectCode}</td>
                            <td>${subject.subjectDescription}</td>
                            <td>
                                <button type="submit" class="btn btn-block btn-outline-warning" onclick="editSubject('${subject.subjectId}','${subject.subjectName}','${subject.subjectCode}','${subject.subjectDescription}')">Edit</button>
                            </td>
                            <td>
                                <form method="post" action="subject">
                                    <input type="hidden" name="subjectId" value="${subject.subjectId}"/>
                                    <input type="hidden" name="method" value="delete"/>
                                    <button type="submit" class="btn btn-block btn-outline-danger"
                                            onclick="return confirm('Are you sure you want to delete this subject?');">
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
<div class="modal fade" id="addSubject" style="display: none;" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Add/Update Subject</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <form id="saveClass" method="post" action="subject">
                <div class="modal-body">
                    <input type="text" class="form-control" id="method" name="method" hidden>
                    <input type="text" class="form-control" id="subjectId" name="subjectId" hidden>
                    <div class="form-group">
                        <label for="subjectName">Subject Name</label>
                        <input type="text" class="form-control" id="subjectName" placeholder="Enter subject name" name="subjectName" required>
                    </div>
                    <div class="form-group">
                        <label for="subjectCode">Subject Code</label>
                        <input type="text" class="form-control" id="subjectCode" placeholder="Enter subject code" name="subjectCode" required>
                    </div>
                    <div class="form-group">
                        <label for="subjectDescription">Subject Description</label>
                        <input type="text" class="form-control" id="subjectDescription" placeholder="Enter subject description" name="subjectDescription" required>
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
    function addSubject(){
        $('#method').val('none');
        $('#subjectId').val('');
        $('#subjectName').val('');
        $('#subjectCode').val('');
        $('#subjectDescription').val('');
        $('#addSubject').modal("show");
    }
    function editSubject(id,name,code,desc){
        $('#method').val('put');
        $('#subjectId').val(id);
        $('#subjectName').val(name);
        $('#subjectCode').val(code);
        $('#subjectDescription').val(desc);
        $('#addSubject').modal("show");
    }
</script>
</div>
<%@ include file="/WEB-INF/common/scripts.html" %>
</body>
</html>
