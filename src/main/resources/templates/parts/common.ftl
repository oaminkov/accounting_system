<#macro page>
    <#include "head.ftl">
        <#include "navbar.ftl">
        <!--КОНТЕНТ-->
        <div class="container">
            <#nested>
        </div>
        <!--/КОНТЕНТ-->
        <#include "scripts.ftl">
    <#include "foot.ftl">
</#macro>