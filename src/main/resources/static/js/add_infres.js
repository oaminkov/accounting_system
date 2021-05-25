jQuery(document).ready(function () {
    function getOptions(result) {
        if (result.type === 'error') {
            alert('error');
            return "";
        }
        else {
            let options = '';

            $(result).each(function(key, val) {
                options += '<option value="' + val.id + '">' + val.name + '</option>';
            });

            return options;
        }
    }

    function bindSelect2() {
        $('.select2').select2();
    }

    function ajaxRequest(requestAddress, id, parentDiv, changedObj, changedObjHtml) {
        $.ajax({
            type: 'GET',
            url: requestAddress,
            data: {
                id: id
            },
            dataType: 'json',
            success: [
                function (result) {
                    let options = getOptions(result);

                    parentDiv.find(changedObj).html(changedObjHtml + options);
                    parentDiv.find(changedObj).attr('disabled', false);
                }
            ]
        });
    }

    function changeTypes() {
        let requestAddress = "/getObservationTypeList",
            idDisc = $(this).val(),
            parentDiv = $(this).parents('.discDiv').first(),
            changedClass = '.getObservationType',
            changedClassHtml = '<option value="0">-- Выберите вид наблюдений --</option>';

        if (idDisc === '0' || idDisc === '-1') {
            parentDiv.find(changedClass).html(changedClassHtml);
            parentDiv.find(changedClass).attr('disabled', true);
            parentDiv.find('.getObservationParameter').html('<option value="0">-- Выберите параметр наблюдений --</option>');
            parentDiv.find('.getObservationParameter').attr('disabled', true);
            return(false);
        }

        parentDiv.find(changedClass).attr('disabled', true);
        parentDiv.find(changedClass).html('<option>загрузка...</option>');
        parentDiv.find('.getObservationParameter').html('<option value="0">-- Выберите параметр наблюдений --</option>');
        parentDiv.find('.getObservationParameter').attr('disabled', true);

        ajaxRequest(requestAddress, idDisc, parentDiv, changedClass, changedClassHtml);
    }

    function changeParameters() {
        let requestAddress = "/getObservationParameterList",
            idType = $(this).val(),
            parentDiv = $(this).parents('.typeDiv').first(),
            changedClass = '.getObservationParameter',
            changedClassHtml = '<option value="0">-- Выберите параметр наблюдений --</option>';

        if (idType === '0' || idType === '-1') {
            parentDiv.find(changedClass).html(changedClassHtml);
            parentDiv.find(changedClass).attr('disabled', true);
            return(false);
        }

        parentDiv.find(changedClass).attr('disabled', true);
        parentDiv.find(changedClass).html('<option>загрузка...</option>');

        ajaxRequest(requestAddress, idType, parentDiv, changedClass, changedClassHtml);
    }

    $('.getObservationDiscipline').on("change", changeTypes);
    $('.getObservationType').on("change", changeParameters);

    function delOrganization() {
        $(this).parents('.organizationDiv').first().remove();
    }

    function delDisc() {
        $(this).parents('.discDiv').first().remove();
    }
    function delType() {
        $(this).parents('.typeDiv').first().remove();
    }
    function delParam() {
        $(this).parents('.paramDiv').first().remove();
    }

    function delScope() {
        $(this).parents('.scopeDiv').first().remove();
    }
    function delTerritory() {
        $(this).parents('.territoryDiv').first().remove();
    }

    function delAllAttachedFiles() {
        $('#delAttachedFiles').val(1);
        $(this).parents('.divAttachedFiles').first().remove();
    }

    let getOrganizationId = 0;

    let getDiscId = 0;
    let getTypeId = 0;
    let getParamId = 0;

    let getScopeId = 0;
    let getTerritoryId = 0;


    function addOrganization() {
        let tpl =   '<div class="organizationDiv">' +
                        '<div class="form-group row">' +
                            '<label class="col-sm-3 col-form-label">Организация:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="organization" class="select2 form-control getOrganization" id="getOrganization'+ getOrganizationId +'">' +
                                    '<option value="0">-- Выберите организацию --</option>' +
                                    organizations +
                                '</select>' +
                                '<div class="input-group-append">' +
                                    '<button type="button" class="btn btn-md btn-danger m-0 ml-1 px-3 py-1 delBtn delOrganization" id="delOrganization'+ getOrganizationId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';
        $(this).parents('div').first().before(tpl);

        bindSelect2();

        $('#delOrganization'+ getOrganizationId).on("click", delOrganization);

        getOrganizationId++;
    }

    function addDisc() {
        let tpl =
            '<div class="discDiv">' +
                '<div class="form-group row">' +
                    '<label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>' +
                    '<div class="col-sm-9 input-group">' +
                        '<select name="observationDiscipline" class="select2 form-control getObservationDiscipline" id="getObservationDiscipline'+ getDiscId +'">' +
                            '<option value="0">-- Выберите дисциплину --</option>' +
                            disciplines +
                        '</select>' +
                        '<div class="input-group-append">' +
                            '<button type="button" class="btn btn-md btn-danger m-0 ml-1 px-3 py-1 delBtn delDisc" id="delDisc'+ getDiscId +'">x</button>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
                '<div class="typeDiv">' +
                    '<div class="form-group row">' +
                        '<label class="col-sm-3 col-form-label">Вид наблюдений:</label>' +
                        '<div class="col-sm-9">' +
                            '<select name="observationType" class="select2 form-control getObservationType" id="getObservationType'+ getTypeId +'" disabled>' +
                                '<option value="0">-- Выберите вид наблюдений --</option>' +
                            '</select>' +
                        '</div>' +
                    '</div>' +
                    '<div class="paramDiv">' +
                        '<div class="form-group row">' +
                            '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                            '<div class="col-sm-9">' +
                                '<select name="observationParameter" class="select2 form-control getObservationParameter" id="getObservationParameter'+ getParamId +'" disabled>' +
                                    '<option value="0">-- Выберите параметр наблюдений --</option>' +
                                '</select>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                    '<div class="form-group row m-0">' +
                        '<button type="button" class="btn btn-primary btn-block addParam" id="addParam'+ getParamId +'">Добавить параметр</button>' +
                    '</div>' +
                    '<hr>' +
                '</div>' +
                '<div class="form-group row m-0">' +
                    '<button type="button" class="btn btn-primary btn-block addType" id="addType'+ getTypeId +'">Добавить вид</button>' +
                '</div>' +
                '<hr>' +
            '</div>';
        $(this).parents('div').first().before(tpl);

        bindSelect2();

        $('#getObservationDiscipline' + getDiscId).on("change", changeTypes);
        $('#getObservationType' + getTypeId).on("change", changeParameters);

        $('#addType' + getTypeId).on("click", addType);
        $('#addParam'+ getParamId).on("click", addParam);

        $('#delDisc'+ getDiscId).on("click", delDisc);

        getDiscId++;
        getTypeId++;
        getParamId++;
    }

    function addType() {
        let requestAddress = "/getObservationTypeList",
            idDisc = $(this).parents('.discDiv').first().find(".getObservationDiscipline").val(),
            parentDiv = $(this).parents('.discDiv').first(),
            changedObj = 'getObservationType' + getTypeId,
            changedObjHtml = '<option value="0">-- Выберите вид наблюдений --</option>';

        let tpl =   '<div class="typeDiv">' +
                        '<div class="form-group row">' +
                            '<label class="col-sm-3 col-form-label">Вид наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationType" class="select2 form-control getObservationType" id="'+ changedObj +'" disabled>' +
                                    changedObjHtml +
                                '</select>' +
                                '<div class="input-group-append">' +
                                    '<button type="button" class="btn btn-md btn-danger m-0 ml-1 px-3 py-1 delBtn delType" id="delType'+ getTypeId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="paramDiv">' +
                            '<div class="form-group row">' +
                                '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                                '<div class="col-sm-9">' +
                                    '<select name="observationParameter" class="select2 form-control getObservationParameter" id="getObservationParameter'+ getParamId +'" disabled>' +
                                        '<option value="0">-- Выберите параметр наблюдений --</option>' +
                                    '</select>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="form-group row m-0">' +
                            '<button type="button" class="btn btn-primary btn-block addParam" id="addParam'+ getParamId +'">Добавить параметр</button>' +
                        '</div>' +
                        '<hr>' +
                    '</div>';
        $(this).parents('div').first().before(tpl);

        bindSelect2();

        $('#' + changedObj).on("change", changeParameters);

        $('#addParam'+ getParamId).on("click", addParam);

        $('#delType'+ getTypeId).on("click", delType);

        getTypeId++;
        getParamId++;

        if (idDisc != 0) {
            ajaxRequest(requestAddress, idDisc, parentDiv, '#' + changedObj, changedObjHtml);
        }
    }

    function addParam() {
        let requestAddress = "/getObservationParameterList",
            idType = $(this).parents('.typeDiv').first().find(".getObservationType").val(),
            parentDiv = $(this).parents('.typeDiv').first(),
            changedObj = 'getObservationParameter' + getParamId,
            changedObjHtml = '<option value="0">-- Выберите параметр наблюдений --</option>';

        let tpl =   '<div class="paramDiv">' +
                        '<div class="form-group row">' +
                            '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationParameter" class="select2 form-control getObservationParameter" id="'+ changedObj +'" disabled>' +
                                    changedObjHtml +
                                '</select>' +
                                '<div class="input-group-append">' +
                                    '<button type="button" class="btn btn-md btn-danger m-0 ml-1 px-3 py-1 delBtn delParam" id="delParam'+ getParamId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';
        $(this).parents('div').first().before(tpl);

        bindSelect2();

        $('#delParam'+ getParamId).on("click", delParam);

        getParamId++;

        if (idType != 0) {
            ajaxRequest(requestAddress, idType, parentDiv, '#' + changedObj, changedObjHtml);
        }
    }

    function addScope() {
        let tpl =   '<div class="scopeDiv">' +
                        '<div class="form-group row">' +
                            '<label class="col-sm-3 col-form-label">Сфера наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationScope" class="select2 form-control getObservationScope" id="getObservationScope'+ getScopeId +'">' +
                                    '<option value="0">-- Выберите сферу наблюдений --</option>' +
                                    scopes +
                                '</select>' +
                                '<div class="input-group-append">' +
                                    '<button type="button" class="btn btn-md btn-danger m-0 ml-1 px-3 py-1 delBtn delScope" id="delScope'+ getScopeId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';

        $(this).parents('div').first().before(tpl);

        bindSelect2();

        $('#delScope'+ getScopeId).on("click", delScope);

        getScopeId++;
    }

    function addTerritory() {
        let tpl =   '<div class="territoryDiv">' +
                        '<div class="form-group row">' +
                            '<label class="col-sm-3 col-form-label">Территория наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationTerritory" class="select2 form-control getObservationTerritory" id="getObservationTerritory' + getTerritoryId +'">' +
                                    '<option value="0">-- Выберите территорию наблюдений --</option>' +
                                    territories +
                                '</select>' +
                                '<div class="input-group-append">' +
                                    '<button type="button" class="btn btn-md btn-danger m-0 ml-1 px-3 py-1 delBtn delTerritory" id="delTerritory'+ getTerritoryId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';

        $(this).parents('div').first().before(tpl);

        bindSelect2();

        $('#delTerritory'+ getTerritoryId).on("click", delTerritory);

        getTerritoryId++;
    }

    $('.addOrganization').on("click", addOrganization);
    $('.addDisc').on("click", addDisc);
    $('.addType').on("click", addType);
    $('.addParam').on("click", addParam);
    $('.addScope').on("click", addScope);
    $('.addTerritory').on("click", addTerritory);

    $('.delOrganization').on("click", delOrganization);
    $('.delDisc').on("click", delDisc);
    $('.delType').on("click", delType);
    $('.delParam').on("click", delParam);
    $('.delScope').on("click", delScope);
    $('.delTerritory').on("click", delTerritory);

    $('.delAllAttachedFiles').on("click", delAllAttachedFiles);
});