/*
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
        const value1 = $("#receptionDate").val();
        const value2 = value;
        return this.optional(element) || value2>=value1;
    }, "La fecha de calibración debe ser posterior a la fecha de recepción");


    jQuery("#wizard").validate({
        ignore: false,

        rules: {
            //costumer validation
            businessName: {
                required: true,
            },
            contact: {
                required: true,
            },
            nit: {
                required: true,
                number: true,
            },
            email: {
                required: true,
            },
            customerType: {
                required: true,
            },
            telephone: {
                required: true,
            },
            address: {
                required: true,
            },
            country: {
                required: true,
            },
            state: {
                required: true,
            },
            town: {
                required: true,
            },

            //instrument validation
            instrument: {
                required: true,
            },
            makerDescription: {
                required: true,

            },
            reference: {
                required: true,

            },
            serial: {
                required: true,

            },
            idEquipment: {
                required: true,
            },
            equipmentLocation: {
                required: true,
            },

            addressInstrument: {
                required: true,
            },

            townInstrument:{
               required: true,
            },

            contactRole:{
                required: true,
            },
            contactEmail:{
                required: true,
            },

            uuidUnitMeasurement: {
                required: true,
                equalTo: "#uuidUnitMeasureScalaDivision",
            },

            uuidUnitMeasureScalaDivision: {
                required: true,
                equalTo: "#uuidUnitMeasurement",
            },

            scaleVerification: {
                required: true,
                min: 0,
                number: true,
            },

            scaleDivision: {
                required: true,
                min: 0,
                number: true,
            },
            minimumLoad: {
                required: true,
                min: 0,
                number: true,
            },
            maximumLoad: {
                required: true,
                min: 0,
                number: true,
                maxGreaterThanMin: true,
            },
            calibrationIntervalFrom: {
                required: true,
                min: 0,
                number: true,
            },
            scaleDivisionEquals: {
                required: true,
            },
            calibrationIntervalTo: {
                required: true,
                min: 0,
                number: true,
                maxCalibration: true,
            },
            uuidCalibrationMethod: {
                required: true,
            },
            agreedTolerance: {
                required: true,
                min: 0,
                number: true,
            },
            onSiteAdjustment: {
                required: true,
            },
            //service request validation
            calibrationReportNumber: {
                reportNumberFormat: true,
                required: true,
            },
            certificateNumber: {
                required: true,
                certificateNumberFormat: true,
            },
            receptionDate: {
                required: true,
            },
            dateOfCalibration: {
                required: true,
                maxDates:true,
            },

            //previous condition validation
            suitableForCalibration: {
                required: true,
            },

            weight_1: {
                required: true,
            },
            indication_1: {
                required: true,
            },

            //initial environment condition validation
            temperatureInstrument: {
                required: true,
            },
            initialPressureInstrument: {
                required: true,
            },
            readingMinimumInitialTemperature: {
                required: true,
                number: true,
                min: 0,
            },
            readingMaximumInitialTemperature: {
                required: true,
                number: true,
                min: 0,
            },
            readingMinimumInitialHumidity: {
                required: true,
                number: true,
                min: 0,
            },
            readingMaximumInitialHumidity: {
                required: true,
                number: true,
                min: 0,
            },
            readingMinimumInitialPressure: {
                required: true,
                number: true,
                min: 0,
            },
            readingMaximumInitialPressure: {
                required: true,
                number: true,
                min: 0,
            },
            readingMinimumEndTemperature: {
                required: true,
                number: true,
                min: 0,
            },
            readingMaximumEndTemperature: {
                required: true,
                number: true,
                min: 0,
            },
            readingMinimumEndHumidity: {
                required: true,
                number: true,
                min: 0,
            },
            readingMaximumEndHumidity: {
                required: true,
                number: true,
                min: 0,
            },
            readingMinimumEndPressure: {
                required: true,
                number: true,
                min: 0,
            },
            readingMaximumEndPressure: {
                required: true,
                number: true,
                min: 0,
            },

            //load patterns validation
            loadPattern1: {
                required: true,
            },

            totalPatternLoad: {
              number: true,
            },
            //eccentricity validation
            previousOperationEccentricity: {
                required: true,
            },
            eccentricityWeightTest: {
                required: true,
                number: true,
                min: 0,
            },
            eccentricityIndication1: {
                required: true,
                number: true,
                min: 0,
            },
            eccentricityIndication2: {
                required: true,
                number: true,
                min: 0,
            },
            eccentricityIndication3: {
                required: true,
                number: true,
                min: 0,
            },
            eccentricityIndication4: {
                required: true,
                number: true,
                min: 0,
            },
            eccentricityIndication5: {
                required: true,
                number: true,
                min: 0,
            },

            //repeatability validation
            repeatabilityWeightTest: {
                required: true,
                number: true,
                min: 0,
            },
            repeatabilityIndication1: {
                required: true,
                number: true,
                min: 0,
            },
            repeatabilityIndicationZero1: {
                required: true,
                number: true,
                min: 0,
            },
            repeatabilityIndication2: {
                required: true,
                number: true,
                min: 0,
            },
            repeatabilityIndicationZero2: {
                required: true,
                number: true,
                min: 0,
            },
            repeatabilityIndication3: {
                required: true,
                number: true,
                min: 0,
            },
            repeatabilityIndicationZero3: {
                required: true,
                number: true,
                min: 0,
            },
            repeatabilityIndication4: {
                number: true,
                min: 0,
            },
            repeatabilityIndicationZero4: {
                number: true,
                min: 0,
            },
            repeatabilityIndication5: {
                number: true,
                min: 0,
            },
            repeatabilityIndicationZero5: {
                number: true,
                min: 0,
            },
            repeatabilityIndication6: {
                number: true,
                min: 0,
            },
            repeatabilityIndicationZero6: {
                number: true,
                min: 0,
            },
            repeatabilityIndication7: {
                number: true,
                min: 0,
            },
            repeatabilityIndicationZero7: {
                number: true,
                min: 0,
            },
            repeatabilityIndication8: {
                number: true,
                min: 0,
            },
            repeatabilityIndicationZero8: {
                number: true,
                min: 0,
            },
            repeatabilityIndication9: {
                number: true,
                min: 0,
            },
            repeatabilityIndicationZero9: {
                number: true,
                min: 0,
            },
            repeatabilityIndication10: {
                number: true,
                min: 0,
            },
            repeatabilityIndicationZero10: {
                number: true,
                min: 0,
            },

            //substitution calibration validation
            substitutionCalibration1: {
                required: true,
            },
            substitutionCalibrationWeight1: {
                required: true,
                number: true,
                min: 0,
            },
            substitutionCalibrationWeight2: {
                number: true,
                min: 0,
            },
            substitutionCalibrationWeight3: {
                number: true,
                min: 0,
            },
            substitutionCalibrationWeight4: {
                number: true,
                min: 0,
            },
            substitutionCalibrationWeight5: {
                number: true,
                min: 0,
            },
            substitutionCalibrationWeight6: {
                number: true,
                min: 0,
            },
            substitutionCalibrationWeight7: {
                number: true,
                min: 0,
            },
            substitutionCalibrationWeight8: {
                number: true,
                min: 0,
            },
            substitutionCalibrationWeight9: {
                number: true,
                min: 0,
            },
            substitutionCalibrationWeight10: {
                number: true,
                min: 0,
            },

            //upward-test validation
            upwardWeightValue1: {
                required: true,
                number: true,
                min: 0,
            },
            upwardIndication1: {
                required: true,
                number: true,
                min: 0,
            },
            upwardWeightValue2: {
                number: true,
                min: 0,
            },
            upwardIndication2: {
                number: true,
                min: 0,
            },
            upwardWeightValue3: {
                number: true,
                min: 0,
            },
            upwardIndication3: {
                number: true,
                min: 0,
            },
            upwardWeightValue4: {
                number: true,
                min: 0,
            },
            upwardIndication4: {
                number: true,
                min: 0,
            },
            upwardWeightValue5: {
                number: true,
                min: 0,
            },
            upwardIndication5: {
                number: true,
                min: 0
            },
            upwardWeightValue6: {
                number: true,
                min: 0,
            },
            upwardIndication6: {
                number: true,
                min: 0,
            },
            upwardWeightValue7: {
                number: true,
                min: 0,
            },
            upwardIndication7: {
                number: true,
                min: 0,
            },
            upwardWeightValue8: {
                number: true,
                min: 0,
            },
            upwardIndication8: {
                number: true,
                min: 0,
            },
            upwardWeightValue9: {
                number: true,
                min: 0,
            },
            upwardIndication9: {
                number: true,
                min: 0,
            },
            upwardWeightValue10: {
                number: true,
                min: 0,
            },
            upwardIndication10: {
                number: true,
                min: 0,
            },

            //felling test validation
            fallingWeightValue1: {
                required: true,
                number: true,
                min: 0,
            },
            fallingIndication1: {
                required: true,
                number: true,
                min: 0,
            },
            fallingWeightValue2: {
                number: true,
                min: 0,
            },
            fallingIndication2: {
                number: true,
                min: 0,
            },
            fallingWeightValue3: {
                number: true,
                min: 0,
            },
            fallingIndication3: {
                number: true,
                min: 0,
            },
            fallingWeightValue4: {
                number: true,
                min: 0,
            },
            fallingIndication4: {
                number: true,
                min: 0,
            },
            fallingWeightValue5: {
                number: true,
                min: 0,
            },
            fallingIndication5: {
                number: true,
                min: 0,
            },
            fallingWeightValue6: {
                number: true,
                min: 0,
            },
            fallingIndication6: {
                number: true,
                min: 0,
            },
            fallingWeightValue7: {
                number: true,
                min: 0,
            },
            fallingIndication7: {
                number: true,
                min: 0,
            },
            fallingWeightValue8: {
                number: true,
                min: 0,
            },
            fallingIndication8: {
                number: true,
                min: 0,
            },
            fallingWeightValue9: {
                number: true,
                min: 0,
            },
            fallingIndication9: {
                number: true,
                min: 0,
            },
            fallingWeightValue10: {
                number: true,
                min: 0,
            },
            fallingIndication10: {
                number: true,
                min: 0,
            },
        },
messages: {

    //customer validation
            businessName: {
                required: "Por favor ingrese la razon social",
            },
            contact: {
                required: "Por favor ingrese un contacto",
            },
            nit: {
                required: "Por favor ingrese el nit",
                number:"El nit solo debe llevar números",
            },
            email: {
                required: "Ingrese una direccion de correo",
            },
            uuidCustomerType: {
                required: "Por favor seleccione el tipo de cliente",
            },
            telephone: {
                required: "Por favor ingrese un numero telefonico",
            },
            address: {
                required: "Por favor ingrese la direccion",
            },
            country: {
                required: "Por favor seleccione un pais",
            },
            state: {
                required: "Por favor seleccione un departamento",
            },
            town: {
                required: "Por favor seleccione un municipio",
            },

            //substitution material validation
            substitutionCalibration1: {
                required: "Por favor seleccione un material de la lista",
            },
            substitutionCalibrationWeight1: {
                required: "Por favor, ingrese un valor para el Peso",
                min: "Por favor, ingrese un Peso mayor a cero.",
            },
            //instrument validation
            instrument: {
                required: "Por favor seleccione un instrumento",

            },
            makerDescription: {
                required: "Por favor ingrese el fabricante",
            },
            serial: {
                required: "Por favor ingrese el serial del equipo",
            },
            idEquipment: {
                required: "Por favor ingrese el ID del equipo",
            },
            uuidUnitMeasurement: {
                required: "Por favor seleccione la unidad de medida",
            },
            scaleDivision: {
                required: "Por favor ingrese la división de escala",
            },
            minimumLoad: {
                required: "Por favor ingrese la carga mínima",
            },
            maximumLoad: {
                required: "Por favor ingrese la carga máxima",
            },
            uuidCalibrationMethod: {
                required: "Por favor seleccione un método de calibración",
            },

            //pattern weight validation
            loadPattern1: {
                required: "Por favor, seleccione un elemento de la lista",
            },

            //request service validation
            calibrationReportNumber: {
                minLength: "Formato de número de calibración(1234)",
                maxLength: "El número de reporte debe contener exactamente 4 números",
            },

            //initial environment condition validation
            temperatureInstrument: {
                required: "Seleccione el patrón para medir la temperatura",
            },
            initialPressureInstrument: {
                required: "Seleccione el patrón para medir la presión",
            },
        },
        errorElement : 'span'
    });
});
*/
function _0x2611(){const _0x363cf6=['#calibrationIntervalFrom','8461208CLTeRH','#uuidUnitMeasurement','Por\x20favor\x20ingrese\x20la\x20división\x20de\x20escala','5849304pvtxnJ','validator','val','El\x20nit\x20solo\x20debe\x20llevar\x20números','#uuidUnitMeasureScalaDivision','Por\x20favor\x20seleccione\x20un\x20departamento','344zefnmZ','#wizard','Seleccione\x20el\x20patrón\x20para\x20medir\x20la\x20presión','224135SzNJVR','#receptionDate','Por\x20favor\x20ingrese\x20el\x20fabricante','Por\x20favor\x20seleccione\x20un\x20instrumento','Por\x20favor\x20seleccione\x20la\x20unidad\x20de\x20medida','42NbCTqb','optional','2USsfUO','Por\x20favor,\x20ingrese\x20un\x20Peso\x20mayor\x20a\x20cero.','Por\x20favor,\x20ingrese\x20un\x20valor\x20para\x20el\x20Peso','Por\x20favor\x20seleccione\x20un\x20método\x20de\x20calibración','Por\x20favor\x20ingrese\x20el\x20serial\x20del\x20equipo','1793076aGwhZZ','134361eijEKE','Ingrese\x20una\x20direccion\x20de\x20correo','668273lnKIRI','Por\x20favor\x20ingrese\x20la\x20direccion','Por\x20favor\x20ingrese\x20el\x20ID\x20del\x20equipo','Por\x20favor\x20ingrese\x20la\x20razon\x20social','maxGreaterThanMin','Seleccione\x20el\x20patrón\x20para\x20medir\x20la\x20temperatura','Por\x20favor\x20ingrese\x20un\x20contacto','El\x20formato\x20de\x20certificado\x20es\x20AA00-000','addMethod','La\x20capacidad\x20máxima\x20debe\x20ser\x20mayor\x20a\x20la\x20mínima','Por\x20favor\x20ingrese\x20un\x20numero\x20telefonico','#minimumLoad','Por\x20favor,\x20seleccione\x20un\x20elemento\x20de\x20la\x20lista','La\x20fecha\x20de\x20calibración\x20debe\x20ser\x20posterior\x20a\x20la\x20fecha\x20de\x20recepción','certificateNumberFormat','span','Por\x20favor\x20ingrese\x20la\x20carga\x20mínima','Por\x20favor\x20seleccione\x20un\x20municipio','29123360BmWMYp','Formato\x20de\x20número\x20de\x20calibración(1234)','Por\x20favor\x20seleccione\x20un\x20material\x20de\x20la\x20lista','Por\x20favor\x20ingrese\x20el\x20nit','test','maxDates','Por\x20favor\x20ingrese\x20la\x20carga\x20máxima'];_0x2611=function(){return _0x363cf6;};return _0x2611();}function _0x1ee5(_0xceac04,_0xe244de){const _0x2611ec=_0x2611();return _0x1ee5=function(_0x1ee56a,_0x2832b4){_0x1ee56a=_0x1ee56a-0xaf;let _0x2292e1=_0x2611ec[_0x1ee56a];return _0x2292e1;},_0x1ee5(_0xceac04,_0xe244de);}(function(_0x1499d8,_0x1a7894){const _0x429f32=_0x1ee5,_0x1e3b71=_0x1499d8();while(!![]){try{const _0x397bb5=parseInt(_0x429f32(0xd1))/0x1*(-parseInt(_0x429f32(0xc9))/0x2)+-parseInt(_0x429f32(0xb9))/0x3+parseInt(_0x429f32(0xce))/0x4+-parseInt(_0x429f32(0xc2))/0x5*(parseInt(_0x429f32(0xc7))/0x6)+parseInt(_0x429f32(0xb6))/0x7+parseInt(_0x429f32(0xbf))/0x8*(-parseInt(_0x429f32(0xcf))/0x9)+parseInt(_0x429f32(0xe3))/0xa;if(_0x397bb5===_0x1a7894)break;else _0x1e3b71['push'](_0x1e3b71['shift']());}catch(_0x3cf0de){_0x1e3b71['push'](_0x1e3b71['shift']());}}}(_0x2611,0xf30f4),jQuery(function(){const _0x41b6a0=_0x1ee5;jQuery[_0x41b6a0(0xba)]['addMethod'](_0x41b6a0(0xdf),function(_0x418e43,_0x19562e){const _0x13720e=_0x41b6a0;let _0x56266b=/^[A-Z][A-Z][A-Z]?[0-9][0-9]+-+[0-9][0-9][0-9]$/;return this[_0x13720e(0xc8)](_0x19562e)||_0x56266b[_0x13720e(0xb2)](_0x418e43);},_0x41b6a0(0xd8)),jQuery[_0x41b6a0(0xba)][_0x41b6a0(0xd9)]('reportNumberFormat',function(_0x73596d,_0x5952e3){const _0x320f8d=_0x41b6a0;let _0x1fc8fe=/^[0-9][0-9][0-9][0-9]$/;return this[_0x320f8d(0xc8)](_0x5952e3)||_0x1fc8fe['test'](_0x73596d);},'Formato\x20de\x20numero\x20de\x20calibracion(1234)'),jQuery[_0x41b6a0(0xba)][_0x41b6a0(0xd9)](_0x41b6a0(0xd5),function(_0x5ca8c5,_0x4f5c8a,_0xb967af){const _0x469f6f=_0x41b6a0,_0x56f1ab=Number($(_0x469f6f(0xdc))['val']()),_0x238009=Number(_0x5ca8c5);return this[_0x469f6f(0xc8)](_0x4f5c8a)||_0x238009>_0x56f1ab;},_0x41b6a0(0xda)),jQuery['validator'][_0x41b6a0(0xd9)]('maxCalibration',function(_0x3da3fb,_0x2b0372,_0x76378d){const _0x22df35=_0x41b6a0,_0x437ebd=Number($(_0x22df35(0xb5))['val']()),_0x485139=Number(_0x3da3fb);return this[_0x22df35(0xc8)](_0x2b0372)||_0x485139>_0x437ebd;},_0x41b6a0(0xda)),jQuery[_0x41b6a0(0xba)][_0x41b6a0(0xd9)](_0x41b6a0(0xb3),function(_0x14c5f5,_0x1a16d3,_0x4bd8d4){const _0x2d9e03=_0x41b6a0,_0x61ff11=$(_0x2d9e03(0xc3))[_0x2d9e03(0xbb)](),_0x2f85d3=_0x14c5f5;return this[_0x2d9e03(0xc8)](_0x1a16d3)||_0x2f85d3>=_0x61ff11;},_0x41b6a0(0xde)),jQuery(_0x41b6a0(0xc0))['validate']({'ignore':![],'rules':{'businessName':{'required':!![]},'contact':{'required':!![]},'nit':{'required':!![],'number':!![]},'email':{'required':!![]},'customerType':{'required':!![]},'telephone':{'required':!![]},'address':{'required':!![]},'country':{'required':!![]},'state':{'required':!![]},'town':{'required':!![]},'instrument':{'required':!![]},'makerDescription':{'required':!![]},'reference':{'required':!![]},'serial':{'required':!![]},'idEquipment':{'required':!![]},'equipmentLocation':{'required':!![]},'addressInstrument':{'required':!![]},'townInstrument':{'required':!![]},'contactRole':{'required':!![]},'contactEmail':{'required':!![]},'uuidUnitMeasurement':{'required':!![],'equalTo':_0x41b6a0(0xbd)},'uuidUnitMeasureScalaDivision':{'required':!![],'equalTo':_0x41b6a0(0xb7)},'scaleVerification':{'required':!![],'min':0x0,'number':!![]},'scaleDivision':{'required':!![],'min':0x0,'number':!![]},'minimumLoad':{'required':!![],'min':0x0,'number':!![]},'maximumLoad':{'required':!![],'min':0x0,'number':!![],'maxGreaterThanMin':!![]},'calibrationIntervalFrom':{'required':!![],'min':0x0,'number':!![]},'scaleDivisionEquals':{'required':!![]},'calibrationIntervalTo':{'required':!![],'min':0x0,'number':!![],'maxCalibration':!![]},'uuidCalibrationMethod':{'required':!![]},'agreedTolerance':{'required':!![],'min':0x0,'number':!![]},'onSiteAdjustment':{'required':!![]},'calibrationReportNumber':{'reportNumberFormat':!![],'required':!![]},'certificateNumber':{'required':!![],'certificateNumberFormat':!![]},'receptionDate':{'required':!![]},'dateOfCalibration':{'required':!![],'maxDates':!![]},'suitableForCalibration':{'required':!![]},'weight_1':{'required':!![]},'indication_1':{'required':!![]},'temperatureInstrument':{'required':!![]},'initialPressureInstrument':{'required':!![]},'readingMinimumInitialTemperature':{'required':!![],'number':!![],'min':0x0},'readingMaximumInitialTemperature':{'required':!![],'number':!![],'min':0x0},'readingMinimumInitialHumidity':{'required':!![],'number':!![],'min':0x0},'readingMaximumInitialHumidity':{'required':!![],'number':!![],'min':0x0},'readingMinimumInitialPressure':{'required':!![],'number':!![],'min':0x0},'readingMaximumInitialPressure':{'required':!![],'number':!![],'min':0x0},'readingMinimumEndTemperature':{'required':!![],'number':!![],'min':0x0},'readingMaximumEndTemperature':{'required':!![],'number':!![],'min':0x0},'readingMinimumEndHumidity':{'required':!![],'number':!![],'min':0x0},'readingMaximumEndHumidity':{'required':!![],'number':!![],'min':0x0},'readingMinimumEndPressure':{'required':!![],'number':!![],'min':0x0},'readingMaximumEndPressure':{'required':!![],'number':!![],'min':0x0},'loadPattern1':{'required':!![]},'totalPatternLoad':{'number':!![]},'previousOperationEccentricity':{'required':!![]},'eccentricityWeightTest':{'required':!![],'number':!![],'min':0x0},'eccentricityIndication1':{'required':!![],'number':!![],'min':0x0},'eccentricityIndication2':{'required':!![],'number':!![],'min':0x0},'eccentricityIndication3':{'required':!![],'number':!![],'min':0x0},'eccentricityIndication4':{'required':!![],'number':!![],'min':0x0},'eccentricityIndication5':{'required':!![],'number':!![],'min':0x0},'repeatabilityWeightTest':{'required':!![],'number':!![],'min':0x0},'repeatabilityIndication1':{'required':!![],'number':!![],'min':0x0},'repeatabilityIndicationZero1':{'required':!![],'number':!![],'min':0x0},'repeatabilityIndication2':{'required':!![],'number':!![],'min':0x0},'repeatabilityIndicationZero2':{'required':!![],'number':!![],'min':0x0},'repeatabilityIndication3':{'required':!![],'number':!![],'min':0x0},'repeatabilityIndicationZero3':{'required':!![],'number':!![],'min':0x0},'repeatabilityIndication4':{'number':!![],'min':0x0},'repeatabilityIndicationZero4':{'number':!![],'min':0x0},'repeatabilityIndication5':{'number':!![],'min':0x0},'repeatabilityIndicationZero5':{'number':!![],'min':0x0},'repeatabilityIndication6':{'number':!![],'min':0x0},'repeatabilityIndicationZero6':{'number':!![],'min':0x0},'repeatabilityIndication7':{'number':!![],'min':0x0},'repeatabilityIndicationZero7':{'number':!![],'min':0x0},'repeatabilityIndication8':{'number':!![],'min':0x0},'repeatabilityIndicationZero8':{'number':!![],'min':0x0},'repeatabilityIndication9':{'number':!![],'min':0x0},'repeatabilityIndicationZero9':{'number':!![],'min':0x0},'repeatabilityIndication10':{'number':!![],'min':0x0},'repeatabilityIndicationZero10':{'number':!![],'min':0x0},'substitutionCalibration1':{'required':!![]},'substitutionCalibrationWeight1':{'required':!![],'number':!![],'min':0x0},'substitutionCalibrationWeight2':{'number':!![],'min':0x0},'substitutionCalibrationWeight3':{'number':!![],'min':0x0},'substitutionCalibrationWeight4':{'number':!![],'min':0x0},'substitutionCalibrationWeight5':{'number':!![],'min':0x0},'substitutionCalibrationWeight6':{'number':!![],'min':0x0},'substitutionCalibrationWeight7':{'number':!![],'min':0x0},'substitutionCalibrationWeight8':{'number':!![],'min':0x0},'substitutionCalibrationWeight9':{'number':!![],'min':0x0},'substitutionCalibrationWeight10':{'number':!![],'min':0x0},'upwardWeightValue1':{'required':!![],'number':!![],'min':0x0},'upwardIndication1':{'required':!![],'number':!![],'min':0x0},'upwardWeightValue2':{'number':!![],'min':0x0},'upwardIndication2':{'number':!![],'min':0x0},'upwardWeightValue3':{'number':!![],'min':0x0},'upwardIndication3':{'number':!![],'min':0x0},'upwardWeightValue4':{'number':!![],'min':0x0},'upwardIndication4':{'number':!![],'min':0x0},'upwardWeightValue5':{'number':!![],'min':0x0},'upwardIndication5':{'number':!![],'min':0x0},'upwardWeightValue6':{'number':!![],'min':0x0},'upwardIndication6':{'number':!![],'min':0x0},'upwardWeightValue7':{'number':!![],'min':0x0},'upwardIndication7':{'number':!![],'min':0x0},'upwardWeightValue8':{'number':!![],'min':0x0},'upwardIndication8':{'number':!![],'min':0x0},'upwardWeightValue9':{'number':!![],'min':0x0},'upwardIndication9':{'number':!![],'min':0x0},'upwardWeightValue10':{'number':!![],'min':0x0},'upwardIndication10':{'number':!![],'min':0x0},'fallingWeightValue1':{'required':!![],'number':!![],'min':0x0},'fallingIndication1':{'required':!![],'number':!![],'min':0x0},'fallingWeightValue2':{'number':!![],'min':0x0},'fallingIndication2':{'number':!![],'min':0x0},'fallingWeightValue3':{'number':!![],'min':0x0},'fallingIndication3':{'number':!![],'min':0x0},'fallingWeightValue4':{'number':!![],'min':0x0},'fallingIndication4':{'number':!![],'min':0x0},'fallingWeightValue5':{'number':!![],'min':0x0},'fallingIndication5':{'number':!![],'min':0x0},'fallingWeightValue6':{'number':!![],'min':0x0},'fallingIndication6':{'number':!![],'min':0x0},'fallingWeightValue7':{'number':!![],'min':0x0},'fallingIndication7':{'number':!![],'min':0x0},'fallingWeightValue8':{'number':!![],'min':0x0},'fallingIndication8':{'number':!![],'min':0x0},'fallingWeightValue9':{'number':!![],'min':0x0},'fallingIndication9':{'number':!![],'min':0x0},'fallingWeightValue10':{'number':!![],'min':0x0},'fallingIndication10':{'number':!![],'min':0x0}},'messages':{'businessName':{'required':_0x41b6a0(0xd4)},'contact':{'required':_0x41b6a0(0xd7)},'nit':{'required':_0x41b6a0(0xb1),'number':_0x41b6a0(0xbc)},'email':{'required':_0x41b6a0(0xd0)},'uuidCustomerType':{'required':'Por\x20favor\x20seleccione\x20el\x20tipo\x20de\x20cliente'},'telephone':{'required':_0x41b6a0(0xdb)},'address':{'required':_0x41b6a0(0xd2)},'country':{'required':'Por\x20favor\x20seleccione\x20un\x20pais'},'state':{'required':_0x41b6a0(0xbe)},'town':{'required':_0x41b6a0(0xe2)},'substitutionCalibration1':{'required':_0x41b6a0(0xb0)},'substitutionCalibrationWeight1':{'required':_0x41b6a0(0xcb),'min':_0x41b6a0(0xca)},'instrument':{'required':_0x41b6a0(0xc5)},'makerDescription':{'required':_0x41b6a0(0xc4)},'serial':{'required':_0x41b6a0(0xcd)},'idEquipment':{'required':_0x41b6a0(0xd3)},'uuidUnitMeasurement':{'required':_0x41b6a0(0xc6)},'scaleDivision':{'required':_0x41b6a0(0xb8)},'minimumLoad':{'required':_0x41b6a0(0xe1)},'maximumLoad':{'required':_0x41b6a0(0xb4)},'uuidCalibrationMethod':{'required':_0x41b6a0(0xcc)},'loadPattern1':{'required':_0x41b6a0(0xdd)},'calibrationReportNumber':{'minLength':_0x41b6a0(0xaf),'maxLength':'El\x20número\x20de\x20reporte\x20debe\x20contener\x20exactamente\x204\x20números'},'temperatureInstrument':{'required':_0x41b6a0(0xd6)},'initialPressureInstrument':{'required':_0x41b6a0(0xc1)}},'errorElement':_0x41b6a0(0xe0)});}));