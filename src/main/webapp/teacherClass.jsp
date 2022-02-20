<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/common/head.html" %>
    <title>Teachers/Classes - Zaghloul Academy</title>
</head>
<body class="sidebar-mini" style="height: auto;">
<%@ include file="/WEB-INF/common/bodyStart.html" %>

<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Assign Teachers to Classes</h3>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 10%">Class Id</th>
                        <th style="width: 30%">Class Name</th>
                        <th style="width: 40%">Teachers Assigned</th>
                        <th style="width: 10%">Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.classList}" var="_class">
                        <tr>
                            <td>${_class.classId}</td>
                            <td>${_class.className}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${_class.teachers.size() > 0}">
                                        <table>
                                            <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Phone</th>
                                                <th>Delete</th>
                                            </tr>
                                            <c:forEach items="${_class.teachers}" var="teacher">
                                            <tr>
                                                <span style="display: none" class="teacherId_${_class.classId}">${teacher.teacherId}</span>
                                                <td>${teacher.teacherName}</td>
                                                <td>${teacher.teacherPhone}</td>
                                                <td>
                                                    <form method="post" action="teacherClass">
                                                        <input type="hidden" name="teacherId" value="${teacher.teacherId}"/>
                                                        <input type="hidden" name="method" value="delete"/>
                                                        <button type="submit" class="btn btn-block btn-outline-danger"
                                                                onclick="return confirm('Are you sure you want to delete this teacherId from the class?');">
                                                            Delete
                                                        </button>
                                                    </form>
                                                </td>
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
                                    <c:when test="${requestScope.teacherList.size() == 0}">
                                        <button type="submit" class="btn btn-block btn-outline-warning" disabled
                                                onclick="editAssign('${_class.classId}')">Edit
                                        </button>
                                        <span>No Teachers or All Assigned</span>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-block btn-outline-warning"
                                                onclick="editAssign('${_class.classId}')">Edit
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
                <h4 class="modal-title">Add/Update Class</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <form id="save" method="post" action="teacherClass">
                <div class="modal-body">
                    <input type="text" class="form-control" id="classId" name="classId" hidden>
                    <div class="form-group">
                        <label>Select Teachers</label>
                        <select multiple="" class="form-control" id="teachers" name="teachers">
                            <c:forEach items="${requestScope.teacherList}" var="teachers">
                                <option value="${teachers.teacherId}">${teachers.teacherName}</option>
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
        $('#classId').val(id);
        var array = [];
        var selector = ".teacherId_"+id;
        $(selector).each(function () {
            array.push(this.innerHTML);
        });
        prepopulateOptions(array);
        $('#editAssign').modal("show");
    }

    function prepopulateOptions(value) {
        var options = $('#teachers option');
        var count = $('#teachers option').length;
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
