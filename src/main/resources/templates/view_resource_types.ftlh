<#import "parts/view_objects.ftlh" as v>
<@v.view "/resource_types/add" "Типы информационных ресурсов" "Добавить тип информационного ресурса">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
        <tr>
            <th>Название типа</th>
        </tr>
        </thead>
        <tbody>
        <#list resourceTypes as resourceType>
            <tr>
                <td>${resourceType.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>