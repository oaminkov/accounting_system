<#import "parts/viewtable.ftl" as v>
<@v.view "/add_country" "Страны" "Добавить страну">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Название страны</th>
            </tr>
        </thead>
        <tbody>
        <#list listCountry as country>
            <tr>
                <td>${country.nameCountry}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>