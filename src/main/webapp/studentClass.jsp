<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/common/head.html" %>
    <title>Student/Classes - Zaghloul Academy</title>
</head>
<body class="sidebar-mini" style="height: auto;">
<%@ include file="/WEB-INF/common/bodyStart.html" %>

<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Assign Students to Classes</h3>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 10%">Class Id</th>
                        <th style="width: 30%">Class Name</th>
                        <th style="width: 40%">Students Assigned</th>
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
                                    <c:when test="${_class.students.size() > 0}">
                                        <table>
                                            <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Phone</th>
                                                <th>Delete</th>
                                            </tr>
                                            <c:forEach items="${_class.students}" var="student">
                                            <tr>
                                                <span style="display: none" class="studentId_${_class.classId}">${student.studentId}</span>
                                                <td>${student.studentName}</td>
                                                <td>${student.studentPhone}</td>
                                                <td>
                                                    <form method="post" action="studentClass">
                                                        <input type="hidden" name="studentId" value="${student.studentId}"/>
                                                        <input type="hidden" name="method" value="delete"/>
                                                        <button type="submit" class="btn btn-block btn-outline-danger"
                                                                onclick="return confirm('Are you sure you want to delete this student from the class?');">
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
                                    <c:when test="${requestScope.studentList.size() == 0}">
                                        <button type="submit" class="btn btn-block btn-outline-warning" disabled
                                                onclick="editAssign('${_class.classId}')">Edit
                                        </button>
                                        <span>No Students or All Assigned</span>
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
            <form id="save" method="post" action="studentClass">
                <div class="modal-body">
                    <input type="text" class="form-control" id="classId" name="classId" hidden>
                    <div class="form-group">
                        <label>Select Student</label>
                        <select multiple="" class="form-control" id="students" name="students">
                            <c:forEach items="${requestScope.studentList}" var="students">
                                <option value="${students.studentId}">${students.studentName}</option>
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
        var selector = ".studentId_"+id;
        $(selector).each(function () {
            array.push(this.innerHTML);
        });
        prepopulateOptions(array);
        $('#editAssign').modal("show");
    }

    function prepopulateOptions(value) {
        var options = $('#students option');
        var count = $('#students option').length;
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
