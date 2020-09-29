<#import "parts/view_table.ftl" as v>
<@v.view "/related_projects/add" "Связанные проекты" "Добавить связанный проект">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Проект/программа</th>
                <th>Полное название проекта/программы</th>
                <th>Аббревиатура проекта/программы</th>
            </tr>
        </thead>
        <tbody>
        <#list relatedProjects as relatedProject>
            <tr>
                <td>${relatedProject.type}</td>
                <td>${relatedProject.fullName}</td>
                <td>${relatedProject.abbreviation}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>