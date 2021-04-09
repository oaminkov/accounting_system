<#import "parts/view_table.ftl" as v>
<@v.view "/sources/add" "Источники" "Добавить источник">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
        <tr>
            <th>Название источника</th>
        </tr>
        </thead>
        <tbody>
        <#list sources as source>
            <tr>
                <td>${source.name}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>