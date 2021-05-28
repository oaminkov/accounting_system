<#macro add path back_path name>
    <#include "*/head.ftlh">
        <#include "*/navbar.ftlh">
        <div class="container">
            <h2 class="mb-3" align="center">${name}</h2>
            <form action="${path}" method="post">
                <#nested>
                <hr>
                <button type="submit" class="btn btn-block btn-success m-0">Сохранить</button>
            </form>
            <a href="${back_path}" class="btn btn-outline-secondary m-0 my-4 waves-effect">Назад</a>
        </div>
        <#include "*/footer.ftlh">

        <#include "*/scripts.ftlh">
        <script type="text/javascript" src="/js/change_types.js"></script>

    <#include "*/foot.ftlh">
</#macro>