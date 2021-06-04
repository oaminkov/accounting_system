<#import "*/macros/view_table.ftlh" as vt>
<@vt.view_table>
    <#include "*/parts/security.ftlh">

    <main class="mb-2" style="width:99%; margin:0 auto;">
        <h2 align="center">Информационные ресурсы</h2>

        <div class="d-flex justify-content-center mb-3">
            <a href="/information_resources/add" class="btn btn-primary">Создать новый информационный ресурс</a>
        </div>

        <table id="table_id" class="table table-striped table-bordered mt-0">
            <thead>
                <tr>
                    <th>Инвентарный номер</th>
                    <th>Язык представления</th>
                    <th>Страна</th>
                    <th>Период наблюдений</th>

                    <#if auth_user??>
                        <th>Прикреплённые файлы</th>
                    </#if>

                    <th>Просмотр</th>

                    <#if auth_user??>
                        <th>Редактирование</th>
                    </#if>
                </tr>
            </thead>
            <tbody>
            <#list informationResources as informationResource>
                <tr>
                    <td>${informationResource.inventoryNumber}</td>
                    <td>${informationResource.language.name}</td>
                    <td>${informationResource.country.name}</td>
                    <td>
                        ${informationResource.dateObservationStart}
                        <br>
                        ${informationResource.dateObservationEnd}
                    </td>

                    <#if auth_user??>
                        <td>
                            <#if informationResource.uploadedFiles?has_content>
                                <div class="btn-group">
                                    <form action="/information_resources/download/${informationResource.id}">
                                        <button type="submit" class="btn btn-sm btn-success">Скачать</button>
                                    </form>
                                    <button type="button" class="btn btn-sm btn-success dropdown-toggle px-2" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false" data-reference="parent">
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <div class="dropdown-menu">
                                        <#list informationResource.uploadedFiles as uploadedFile>
                                            <a href="/file/${informationResource.country.id}/${informationResource.mainOrganization.id}/${informationResource.inventoryNumber}/${uploadedFile.name}"
                                               class="dropdown-item" target="_blank">${uploadedFile.name}</a>
                                        </#list>
                                    </div>
                                </div>
                            <#else>
                                <p align="center">Файлы отсутствуют</p>
                            </#if>
                        </td>
                    </#if>

                    <td>
                        <form action="/information_resources/${informationResource.id}">
                            <button type="submit" class="btn btn-sm btn-info m-0">Просмотреть</button>
                        </form>
                    </td>

                    <#if auth_user??>
                        <td>
                            <form action="/information_resources/edit/${informationResource.id}">
                                <button type="submit" class="btn btn-sm btn-warning m-0">Редактировать</button>
                            </form>
                        </td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
    </main>
</@vt.view_table>