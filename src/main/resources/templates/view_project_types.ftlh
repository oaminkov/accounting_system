<#import "parts/view_table.ftlh" as v>
<@v.view "/project_types/add" "Типы проектов" "Добавить тип проекта">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
        <tr>
            <th>Название типа</th>
        </tr>
        </thead>
        <tbody>
        <#list projectTypes as projectType>
            <tr>
                <td>${projectType.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>