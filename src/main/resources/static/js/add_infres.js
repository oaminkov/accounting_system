$(document).ready(function () {
    function bindSelect2() {
        $('.select2').select2();
    }

    function getOptions(result) {
        if (result.type === 'error') {
            alert('error');
            return "";
        } else {
            let options = '';

            $(result).each(function(key, val) {
                options += '<option value="' + val.id + '">' + val.name + '</option>';
            });

            return options;
        }
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

    function delAdditionalBlock() {
        $(this).parents('.additionalBlock').first().remove();
    }

    function delAllAttachedFiles() {
        $('#delAttachedFiles').val(1);
        $(this).parents('.divAttachedFiles').first().remove();
    }

    let getDiscDivId = 0;
    let getTypeDivId = 0;
    let getParamDivId = 0;

    let getOrgDivId = 0;
    let getScopeDivId = 0;
    let getTerrDivId = 0;

    function addDisc() {
        let changedObj = 'getObservationDiscipline' + getDiscDivId;

        let block =
            '<div class="discDiv additionalBlock">' +
                '<div class="form-group row">' +
                    '<label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>' +
                    '<div class="col-sm-9 input-group">' +
                        '<select name="observationDiscipline" class="select2 form-control getObservationDiscipline" id="getObservationDiscipline'+ getDiscDivId +'">' +
                            '<option value="0">-- Выберите дисциплину --</option>' +
                            disciplines +
                        '</select>' +
                        '<div class="input-group-append ml-1">' +
                            '<button type="button" class="btn btn-md btn-danger btn-block delBtn" id="delDisc'+ getDiscDivId +'">x</button>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
                '<div class="form-group row">' +
                    '<div class="col-9 ml-auto">' +
                        '<button type="button" class="btn btn-outline-primary btn-block waves-effect addType" id="addType'+ getDiscDivId +'">Добавить вид</button>' +
                    '</div>' +
                '</div>' +
                '<hr>' +
            '</div>';
        $(this).parent('div').parent('div').before(block);

        bindSelect2();

        $('#' + changedObj).on("change", changeTypes);

        $('#delDisc'+ getDiscDivId).on("click", delAdditionalBlock);

        $('#addType' + getDiscDivId).on("click", addType);

        getDiscDivId++;
    }

    function addType() {
        let requestAddress = "/getObservationTypeList",
            idDisc = $(this).parents('.discDiv').first().find(".getObservationDiscipline").val(),
            parentDiv = $(this).parents('.discDiv').first(),
            changedObj = 'getObservationType' + getTypeDivId,
            changedObjHtml = '<option value="0">-- Выберите вид наблюдений --</option>';

        let block = '<div class="typeDiv additionalBlock">' +
                        '<div class="form-group row">' +
                            '<label class="col-sm-3 col-form-label">Вид наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationType" class="select2 form-control getObservationType" id="'+ changedObj +'" disabled>' +
                                    changedObjHtml +
                                '</select>' +
                                '<div class="input-group-append ml-1">' +
                                    '<button type="button" class="btn btn-md btn-danger btn-block delBtn" id="delType'+ getTypeDivId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="form-group row">' +
                            '<div class="col-9 ml-auto">' +
                                '<button type="button" class="btn btn-outline-primary btn-block waves-effect addParam" id="addParam'+ getTypeDivId +'">Добавить параметр</button>' +
                            '</div>' +
                        '</div>' +
                        '<hr>' +
                    '</div>';
        $(this).parent('div').parent('div').before(block);

        bindSelect2();

        $('#' + changedObj).on("change", changeParameters);

        $('#delType'+ getTypeDivId).on("click", delAdditionalBlock);

        $('#addParam'+ getTypeDivId).on("click", addParam);

        getTypeDivId++;

        if (idDisc != 0) {
            ajaxRequest(requestAddress, idDisc, parentDiv, '#' + changedObj, changedObjHtml);
        }
    }

    function addParam() {
        let requestAddress = "/getObservationParameterList",
            idType = $(this).parents('.typeDiv').first().find(".getObservationType").val(),
            parentDiv = $(this).parents('.typeDiv').first(),
            changedObj = 'getObservationParameter' + getParamDivId,
            changedObjHtml = '<option value="0">-- Выберите параметр наблюдений --</option>';

        let block =   '<div class="paramDiv additionalBlock">' +
                        '<div class="form-group row mb-0">' +
                            '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationParameter" class="select2 form-control getObservationParameter" id="'+ changedObj +'" disabled>' +
                                    changedObjHtml +
                                '</select>' +
                                '<div class="input-group-append ml-1">' +
                                    '<button type="button" class="btn btn-md btn-danger btn-block delBtn" id="delParam'+ getParamDivId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';
        $(this).parent('div').parent('div').before(block);

        bindSelect2();

        $('#delParam'+ getParamDivId).on("click", delAdditionalBlock);

        getParamDivId++;

        if (idType != 0) {
            ajaxRequest(requestAddress, idType, parentDiv, '#' + changedObj, changedObjHtml);
        }
    }

    function addOrganization() {
        let block = '<div class="additionalBlock">' +
                        '<div class="form-group row">' +
                            '<label class="col-sm-3 col-form-label">Организация:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="organization" class="select2 form-control getOrganization" id="getOrganization'+ getOrgDivId +'">' +
                                    '<option value="0">-- Выберите организацию --</option>' +
                                    organizations +
                                '</select>' +
                                '<div class="input-group-append ml-1">' +
                                    '<button type="button" class="btn btn-md btn-danger btn-block delBtn" id="delOrganization'+ getOrgDivId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';
        $(this).parent('div').parent('div').before(block);

        bindSelect2();

        $('#delOrganization'+ getOrgDivId).on("click", delAdditionalBlock);

        getOrgDivId++;
    }

    function addScope() {
        let block = '<div class="additionalBlock">' +
                        '<div class="form-group row">' +
                            '<label class="col-sm-3 col-form-label">Сфера наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationScope" class="select2 form-control getObservationScope" id="getObservationScope'+ getScopeDivId +'">' +
                                    '<option value="0">-- Выберите сферу наблюдений --</option>' +
                                    scopes +
                                '</select>' +
                                '<div class="input-group-append ml-1">' +
                                    '<button type="button" class="btn btn-md btn-danger btn-block delBtn" id="delScope'+ getScopeDivId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';

        $(this).parent('div').parent('div').before(block);

        bindSelect2();

        $('#delScope'+ getScopeDivId).on("click", delAdditionalBlock);

        getScopeDivId++;
    }

    function addTerritory() {
        let block = '<div class="additionalBlock">' +
                        '<div class="form-group row">' +
                            '<label class="col-sm-3 col-form-label">Территория наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationTerritory" class="select2 form-control getObservationTerritory" id="getObservationTerritory' + getTerrDivId +'">' +
                                    '<option value="0">-- Выберите территорию наблюдений --</option>' +
                                    territories +
                                '</select>' +
                                '<div class="input-group-append ml-1">' +
                                    '<button type="button" class="btn btn-md btn-danger btn-block delBtn" id="delTerritory'+ getTerrDivId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';
        $(this).parent('div').parent('div').before(block);

        bindSelect2();

        $('#delTerritory'+ getTerrDivId).on("click", delAdditionalBlock);

        getTerrDivId++;
    }

    $('.addDisc').on("click", addDisc);
    $('.addType').on("click", addType);
    $('.addParam').on("click", addParam);

    $('.addOrganization').on("click", addOrganization);
    $('.addScope').on("click", addScope);
    $('.addTerritory').on("click", addTerritory);

    $('.delBtn').on("click", delAdditionalBlock);

    $('.delAllAttachedFiles').on("click", delAllAttachedFiles);
});