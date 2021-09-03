<#import "macros/view_objects.ftlh" as v>
<@v.view "/countries/add" "Страны" "Добавить страну">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Название страны</th>
            </tr>
        </thead>
        <tbody>
        <#list countries as country>
            <tr>
                <td>${country.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>