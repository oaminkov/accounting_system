<#import "macros/view_objects.ftlh" as v>
<@v.view "/organizations/add" "Организации" "Добавить организацию">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Оригинальное название организации</th>
                <th>Аббревиатура организации</th>
                <th>Название организации на русском языке</th>
                <th>Страна организации</th>
            </tr>
        </thead>
        <tbody>
        <#list organizations as organization>
            <tr>
                <td>${organization.name}</td>
                <td>${organization.abbreviation}</td>
                <td>${organization.nameRus}</td>

                <#if organization.country?has_content>
                    <td>${organization.country.name}</td>
                <#else>
                    <td></td>
                </#if>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>