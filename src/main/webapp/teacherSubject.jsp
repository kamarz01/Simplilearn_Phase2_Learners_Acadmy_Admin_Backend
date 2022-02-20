<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/common/head.html" %>
    <title>Teachers/Subject - Zaghloul Academy</title>
</head>
<body class="sidebar-mini" style="height: auto;">
<%@ include file="/WEB-INF/common/bodyStart.html" %>

<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Assign Subjects to Teachers</h3>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 10%">Teacher Id</th>
                        <th style="width: 30%">Teacher Name</th>
                        <th style="width: 40%">Subjects Assigned</th>
                        <th style="width: 10%">Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.teacherList}" var="teacher">
                        <tr>
                            <td>${teacher.teacherId}</td>
                            <td>${teacher.teacherName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${teacher.subjects.size() > 0}">
                                        <table>
                                            <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Code</th>
                                            </tr>
                                            <c:forEach items="${teacher.subjects}" var="subject">
                                            <tr>
                                                <span style="display: none" class="subjectId_${teacher.teacherId}">${subject.subjectId}</span>
                                                <td>${subject.subjectName}</td>
                                                <td>${subject.subjectCode}</td>
                                            </tr>
                                            </c:forEach>
                                        </table>
                                    </c:when>
                                    <c:otherwise>
                                        None
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${requestScope.subjectList.size() == 0}">
                                        <button type="submit" class="btn btn-block btn-outline-warning" disabled
                                                onclick="editAssign('${teacher.teacherId}')">Edit
                                        </button>
                                        <span>Add Subjects First</span>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-block btn-outline-warning"
                                                onclick="editAssign('${teacher.teacherId}')">Edit
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editAssign" style="display: none;" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Update Teachers</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <form id="save" method="post" action="teacherSubject">
                <div class="modal-body">
                    <input type="text" class="form-control" id="teacherId" name="teacherId" hidden>
                    <div class="form-group">
                        <label>Select Subjects</label>
                        <select multiple="" class="form-control" id="subjects" name="subjects">
                            <c:forEach items="${requestScope.subjectList}" var="subjects">
                                <option value="${subjects.subjectId}">${subjects.subjectName}</option>
                            </c:forEach>
                        </select>
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
    function editAssign(id) {
        $('#teacherId').val(id);
        var array = [];
        var selector = ".subjectId_"+id;
        $(selector).each(function () {
            array.push(this.innerHTML);
        });
        prepopulateOptions(array);
        $('#editAssign').modal("show");
    }

    function prepopulateOptions(value) {
        var options = $('#subjects option');
        var count = $('#subjects option').length;
        for (var i = 0; i < count; i++) {
            if (value.includes(options[i].value)) {
                options[i].selected = true;
            }
        }
    }
</script>
</div>
<%@ include file="/WEB-INF/common/scripts.html" %>
</body>
</html>
