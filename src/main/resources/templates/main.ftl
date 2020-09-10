<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <#if auth_user??>
        Здравствуйте. Вы  авторизованы как <span style="color:red;"> ${auth_username}</span><br><br>
    <#elseif message?has_content>
        <div>${message}</div>
    </#if>
</@c.page>