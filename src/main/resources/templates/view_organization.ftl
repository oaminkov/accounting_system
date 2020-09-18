<#import "parts/viewtable.ftl" as v>
<@v.view "/add_organization" "Организации" "Добавить организацию">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Полное название организации</th>
                <th>Аббревиатура организации</th>
                <th>Страна организации</th>
            </tr>
        </thead>
        <tbody>
        <#list listOrganization as organization>
            <tr>
                <td>${organization.fullName}</td>
                <td>${organization.abbreviation}</td>
                <td>${organization.country.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>