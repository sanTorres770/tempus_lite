jQuery(function () {

    //validation method for certificate number
    jQuery.validator.addMethod("certificateNumberFormat", function (value, element) {
        let pattern = /^[A-Z][A-Z][A-Z]?[0-9][0-9]+-+[0-9][0-9][0-9]$/;
        return this.optional(element) || pattern.test(value);
    }, "El formato de certificado es AA00-000");

    // validation for Calibration Report Number
    jQuery.validator.addMethod("reportNumberFormat", function (value, element) {
        let pattern = /^[0-9][0-9][0-9][0-9]$/;
        return this.optional(element) || pattern.test(value);
    }, "Formato de numero de calibracion(1234)");

    // validation for interval of measurement
    jQuery.validator.addMethod("maxGreaterThanMin", function (value, element, param) {
        const value1 = Number($("#minimumLoad").val());
        const value2 = Number(value);
        return this.optional(element) || value2>value1;
    }, "La capacidad máxima debe ser mayor a la mínima");

    //validation for interval of Calibration
    jQuery.validator.addMethod("maxCalibration", function (value, element, param) {
        const value1 = Number($("#calibrationIntervalFrom").val());
        const value2 = Number(value);
        return this.optional(element) || value2>value1;
    }, "La capacidad máxima debe ser mayor a la mínima");

    //validation for dates
    jQuery.validator.addMethod("maxDates", function (value, element, param) {
        const value1 = $("#initialDate").val();
        const value2 = value;
        return this.optional(element) || value2>=value1;
    }, "La fecha final debe ser posterior a la fecha de inicio");


    jQuery("#wizard").validate({
        ignore: false,

        rules: {
            //costumer validation
            name: {
                required: true,
            },
            idDocument: {
                required: true,
            },
            fkAreaBoss: {
                required: true,
            },
            employeeId: {
                required: true,
            },
            initialDate: {
                required: true,
            },
            finalDate: {
                required: true,
                maxDates:true,
            },
            progress: {
                required: true,
                number:true,
                min:0,
                max:100,
            },
        },

        messages: {

            name: {
                required: "** Por favor ingrese el nombre",
            },
            idDocument: {
                required: "** Por favor ingrese el documento",
            },
            fkAreaBoss: {
                required: "** Debe seleccionar un jefe de área",
            },
            employeeId: {
                required: "** Debe seleccionar un operario",
            },
            progress: {
                required: "** Debe ingresar el porcentaje de progreso",
                max:"** El progreso dede se menor o igual a 100",
            },
        },
        errorElement : 'span',
    });
});
