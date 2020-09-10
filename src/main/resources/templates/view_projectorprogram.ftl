<#import "parts/viewtable.ftl" as v>
<@v.view "/add_projectorprogram" "Проекты и программы" "Добавить проект/программу">
    <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
        <thead>
            <tr>
                <th>Проект/программа</th>
                <th>Полное название проекта/программы</th>
                <th>Аббревиатура проекта/программы</th>
            </tr>
        </thead>
        <tbody>
        <#list listProjectOrProgram as projectOrProgram>
            <tr>
                <td>${projectOrProgram.choiceProjectOrProgram}</td>
                <td>${projectOrProgram.fullnameProjectOrProgram}</td>
                <td>${projectOrProgram.abbreviationProjectOrProgram}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@v.view>