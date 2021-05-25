<#import "parts/view_table.ftlh" as v>
<@v.view "/languages/add" "Языки" "Добавить язык">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Название языка</th>
            </tr>
        </thead>
        <tbody>
        <#list languages as language>
            <tr>
                <td>${language.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>