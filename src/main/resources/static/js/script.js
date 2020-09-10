jQuery(document).ready(function () {
    $('#getObservationDiscipline').change(function () {
        var idObservationDiscipline = $(this).val();

        if (idObservationDiscipline === '0' || idObservationDiscipline === '-1') {
            $('#getObservationType').html('<option value="0">-- Выберите вид наблюдений --</option>');
            $('#getObservationType').attr('disabled', true);
            return(false);
        }

        $('#getObservationType').attr('disabled', true);
        $('#getObservationType').html('<option>загрузка...</option>');

        $.get(
            "/getObservationTypeList",
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

                    $('#getObservationType').html('<option value="0">-- Выберите вид наблюдений --</option><option value="-1">Описание отсутствует</option>' + options);
                    $('#getObservationType').attr('disabled', false);
                }
            },
            "json");
    });
});