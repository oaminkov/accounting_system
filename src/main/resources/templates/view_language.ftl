<#import "parts/viewtable.ftl" as v>
<@v.view "/add_language" "Языки" "Добавить язык">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Название языка</th>
            </tr>
        </thead>
        <tbody>
        <#list listLanguage as language>
            <tr>
                <td>${language.nameLanguage}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>