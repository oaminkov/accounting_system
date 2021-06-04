<#import "*/macros/common.ftlh" as c>
<#include "*/parts/security.ftlh">
<@c.page>
    <main class="container">
        <#if auth_user??>
            Здравствуйте. Вы  авторизованы как <span style="color:red;"> ${auth_username}</span><br><br>
        <#elseif message?has_content>
            <div>${message}</div>
        </#if>
    </main>

    <#include "*/parts/footer.ftlh">
    <#include "*/parts/scripts.ftlh">
</@c.page>