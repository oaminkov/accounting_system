<#macro view path name namebtn>
    <#import "*/view_table.ftlh" as vt>
    <@vt.view_table>
        <main class="container mb-2">
            <h2 align="center">${name}</h2>

            <div class="d-flex justify-content-center mb-3">
                <a href="${path}" class="btn btn-primary">${namebtn}</a>
            </div>

            <#nested>

        </main>
    </@vt.view_table>
</#macro>