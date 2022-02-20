<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/common/head.html" %>
    <title>Classes - Zaghloul Academy</title>
</head>
<body class="sidebar-mini" style="height: auto;">
<%@ include file="/WEB-INF/common/bodyStart.html" %>
<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Classes List</h3>
                <div class="card-tools">
                    <button type="button" class="btn btn-block btn-outline-success" onclick="addClass()">Add Class</button>
                </div>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 10%">Id</th>
                        <th style="width: 30%">Name</th>
                        <th style="width: 40%">Year</th>
                        <th style="width: 10%">Edit</th>
                        <th style="width: 10%">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.classList}" var="_class">
                        <tr>
                            <td>${_class.classId}</td>
                            <td>${_class.className}</td>
                            <td>${_class.classYear}</td>
                            <td>
                                <button type="submit" class="btn btn-block btn-outline-warning" onclick="editClass('${_class.classId}','${_class.className}','${_class.classYear}')">Edit</button>
                            </td>
                            <td>
                                <form method="post" action="classes">
                                    <input type="hidden" name="classId" value="${_class.classId}"/>
                                    <input type="hidden" name="method" value="delete"/>
                                    <button type="submit" class="btn btn-block btn-outline-danger"
                                            onclick="return confirm('Are you sure you want to delete this class?');">
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
<div class="modal fade" id="addClass" style="display: none;" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Add/Update Class</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <form id="saveClass" method="post" action="classes">
            <div class="modal-body">
                    <input type="text" class="form-control" id="method" name="method" hidden>
                    <input type="text" class="form-control" id="classId" name="classId" hidden>
                    <div class="form-group">
                        <label for="classname">Class Name</label>
                        <input type="text" class="form-control" id="classname" placeholder="Enter class name" name="className" required>
                    </div>
                    <div class="form-group">
                        <label>Class Year</label>
                        <select class="form-control" name="classYear" id="classyear" required>
                            <option value="2020">2020</option>
                            <option value="2021">2021</option>
                            <option value="2022">2022</option>
                            <option value="2023">2023</option>
                            <option value="2024">2024</option>
                            <option value="2025">2025</option>
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
    function addClass(){
        $('#method').val('none');
        $('#classId').val('');
        $('#classname').val('');
        $('#classyear').val('2020').change();
        $('#addClass').modal("show");
    }
    function editClass(id,name,year){
        $('#method').val('put');
        $('#classId').val(id);
        $('#classname').val(name);
        $('#classyear').val(year).change();;
        $('#addClass').modal("show");
    }
</script>
</div>
<%@ include file="/WEB-INF/common/scripts.html" %>
</body>
</html>
