<#macro view_table>
    <#include "*/head.ftlh">
        <#include "*/navbar.ftlh">

        <#nested>

    <#include "*/footer.ftlh">

    <#include "*/scripts.ftlh">
    <script type="text/javascript">
        $(document).ready(function() {
            $('#table_id').DataTable( {
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

    <#include "*/foot.ftlh">
</#macro>