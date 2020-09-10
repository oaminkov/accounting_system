<#import "common.ftl" as c>
<#macro add path backpath name>
    <@c.page>
        <h1 align="center" class="mb-3">${name}</h1>
        <form action="${path}" method="post">
            <#nested>
            <button type="submit" class="btn btn-indigo btn-block m-0 mt-4">Сохранить</button>
        </form>
        <a href="${backpath}" class="btn btn-blue-grey m-0 mt-4">Назад</a>
    </@c.page>
</#macro>