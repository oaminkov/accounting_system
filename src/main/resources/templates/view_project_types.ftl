<#import "parts/viewtable.ftl" as v>
<@v.view "/project_types/add" "Проекты и программы" "Добавить проект/программу">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Проект/программа</th>
                <th>Полное название проекта/программы</th>
                <th>Аббревиатура проекта/программы</th>
            </tr>
        </thead>
        <tbody>
        <#list listProjectType as projectType>
            <tr>
                <td>${projectType.type}</td>
                <td>${projectType.fullName}</td>
                <td>${projectType.abbreviation}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>