<#import "macros/view_objects.ftlh" as v>
<@v.view "/related_projects/add" "Связанные проекты" "Добавить связанный проект">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Тип проекта</th>
                <th>Полное название связанного проекта</th>
                <th>Аббревиатура связанного проекта</th>
            </tr>
        </thead>
        <tbody>
        <#list relatedProjects as relatedProject>
            <tr>
                <td>${relatedProject.projectType.name}</td>
                <td>${relatedProject.name}</td>
                <td>${relatedProject.abbreviation}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>