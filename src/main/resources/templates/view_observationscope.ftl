<#import "parts/viewtable.ftl" as v>
<@v.view "/add_observationscope" "Сферы наблюдений" "Добавить сферу наблюдений">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Сфера наблюдений</th>
            </tr>
        </thead>
        <tbody>
        <#list listObservationScope as observationScope>
            <tr>
                <td>${observationScope.nameObservationScope}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>