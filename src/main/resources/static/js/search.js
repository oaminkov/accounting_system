jQuery(document).ready(function () {
    Array.prototype.remove = function(value) {
        var idx = this.indexOf(value);
        if (idx != -1) {
            // Второй параметр - число элементов, которые необходимо удалить
            return this.splice(idx, 1);
        }
        return false;
    }

    $('#getObservationDiscipline').change(function () {
        var idObservationDiscipline = $(this).val();

        if (idObservationDiscipline === '0') {
            $('#getObservationType').html('<option value="0">-- Выберите вид наблюдений --</option>');
            $('#getCountry').html('<option value="0">-- Выберите страну --</option>');
            $('#getOrganization').html('<option value="0">-- Выберите организацию --</option>');
            $('#getObservationType').attr('disabled', true);
            $('#getCountry').attr('disabled', true);
            $('#getOrganization').attr('disabled', true);
            $('#searchButton').attr('disabled', true);
            return(false);
        }

        $('#getObservationType').attr('disabled', true);
        $('#getObservationType').html('<option>загрузка...</option>');

        $.get(
            "/getObservationTypeList1",
            {idObservationDiscipline: idObservationDiscipline},
            function (result)
            {
                if (result.type === 'error')
                {
                    alert('error');
                    return(false);
                }
                else
                {
                    var options = '';

                    $(result).each(function(key, val)
                    {
                        options += '<option value="' + val.id + '">' + val.name + '</option>';
                    });

                    $('#getObservationType').html('<option value="0">-- Выберите вид наблюдений --</option>' + options);
                    $('#getObservationType').attr('disabled', false);

                    $('#getCountry').html('<option value="0">-- Выберите страну --</option>');
                    $('#getOrganization').html('<option value="0">-- Выберите организацию --</option>');
                    $('#getCountry').attr('disabled', true);
                    $('#getOrganization').attr('disabled', true);

                    $('#searchButton').attr('disabled', false);
                }
            },
            "json");
    });

    $('#getObservationType').change(function () {
        var idObservationType = $(this).val();

        if (idObservationType === '0') {
            $('#getCountry').html('<option value="0">-- Выберите страну --</option>');
            $('#getOrganization').html('<option value="0">-- Выберите организацию --</option>');
            $('#getCountry').attr('disabled', true);
            $('#getOrganization').attr('disabled', true);
            return(false);
        }

        $('#getCountry').attr('disabled', true);
        $('#getCountry').html('<option>загрузка...</option>');

        $.get(
            "/getCountryList1",
            {idObservationType: idObservationType},
            function (result)
            {
                if (result.type === 'error')
                {
                    alert('error');
                    return(false);
                }
                else
                {
                    var options = '';

                    $(result).each(function(key, val)
                    {
                        options += '<option value="' + val.id + '">' + val.name + '</option>';
                    });

                    $('#getCountry').html('<option value="0">-- Выберите страну --</option>' + options);
                    $('#getCountry').attr('disabled', false);

                    $('#getOrganization').html('<option value="0">-- Выберите организацию --</option>');
                    $('#getOrganization').attr('disabled', true);
                    $('#searchButton').attr('disabled', false);
                }
            },
            "json");
    });

    $('#getCountry').change(function () {
        var idCountry = $(this).val();
        var idObservationType = $('#getObservationType').val();

        if (idCountry === '0') {
            $('#getOrganization').html('<option value="0">-- Выберите организацию --</option>');
            $('#getOrganization').attr('disabled', true);
            return(false);
        }

        $('#getOrganization').attr('disabled', true);
        $('#getOrganization').html('<option>загрузка...</option>');

        $.get(
            "/getOrganizationList1",
            {
                idCountry: idCountry,
                idObservationType: idObservationType},
            function (result)
            {
                if (result.type === 'error')
                {
                    alert('error');
                    return(false);
                }
                else
                {
                    var options = '';

                    $(result).each(function(key, val)
                    {
                        options += '<option value="' + val.id + '">' + val.name + '</option>';
                    });

                    $('#getOrganization').html('<option value="0">-- Выберите организацию --</option>' + options);
                    $('#getOrganization').attr('disabled', false);
                    $('#searchButton').attr('disabled', false);
                }
            },
            "json");
    });
});