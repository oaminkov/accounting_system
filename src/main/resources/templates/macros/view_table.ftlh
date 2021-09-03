<#macro view_table>
    <#import "*/macros/common.ftlh" as c>
    <@c.page>
        <#nested>

        <#include "*/parts/footer.ftlh">
        <#include "*/parts/scripts.ftlh">
        <script type="text/javascript">
            $(document).ready(function () {
                $('#table_id').DataTable({
                    paging: true,
                    info: true,
                    searching: true,
                    fixedHeader: {
                        header: true,
                        headerOffset: $('.navbar').height() + 15
                    },
                    language: {
                        url: "//cdn.datatables.net/plug-ins/1.10.20/i18n/Russian.json"
                    }
                });
            });
        </script>
    </@c.page>
</#macro>