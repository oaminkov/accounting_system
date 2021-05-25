<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <#if auth_user??>
        Здравствуйте. Вы  авторизованы как <span style="color:red;"> ${auth_username}</span><br><br>
    <#elseif message?has_content>
        <div>${message}</div>
    </#if>
</@c.page>