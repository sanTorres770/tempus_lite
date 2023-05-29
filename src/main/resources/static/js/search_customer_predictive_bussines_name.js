/*$( function() {
    $("#businessName").autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "/Customer/like/business_name",
                type: 'get',
                dataType: "json",
                data: {
                    businessName: request.term
                },
                success: function (data) {
                    let customers = data.map( customer =>  {
                        return {
                            "label": customer.businessName,
                            "value": customer
                        }
                    });
                    response(customers);
                }
            });
        },
        minLength : 3,
        select: function (event, ui) {
            let theCustomer = ui.item.value;
            populateCustomer(theCustomer);
            return false;
        },
        focus: function (event, ui) {
            clearCustomer();
            let theItem = ui.item;
            $("#businessName").val(theItem.label);
            return false;
        },
    });

    function populateCustomer(customer){
        $('#nit').val(customer.nit);
        $('#businessName').val(customer.businessName);
        $('#contact').val(customer.contact);
        $('#email').val(customer.email);
        $('#telephone').val(customer.telephone);
        $('#address').val(customer.address);
        $('#customerType').val(customer.customerTypeUUID);
        $('#country').val(customer.countryUUID);
        searchStateByCountryId();
        $('#state').val(customer.stateUUID);
        searchTownByStateId();
        $('#town').val(customer.townUUID);
    }

    function clearCustomer(customer){
        $('#nit').val('');
        $('#businessName').val('');
        $('#contact').val('');
        $('#email').val('');
        $('#telephone').val('');
        $('#address').val('');
        $('#customerType').val('');
        $('#country').val('');
        searchStateByCountryId();
        $('#state').val('');
        searchTownByStateId();
        $('#town').val('');
    }
});*/

function _0x212c(_0x2c30a4,_0x5329bd){const _0x1da4cd=_0x1da4();return _0x212c=function(_0x212c3e,_0x58cae4){_0x212c3e=_0x212c3e-0x73;let _0x123c8f=_0x1da4cd[_0x212c3e];return _0x123c8f;},_0x212c(_0x2c30a4,_0x5329bd);}(function(_0x757887,_0x2e50de){const _0x3c0475=_0x212c,_0x56c7c8=_0x757887();while(!![]){try{const _0x7644be=parseInt(_0x3c0475(0x74))/0x1+parseInt(_0x3c0475(0x7e))/0x2+-parseInt(_0x3c0475(0x7d))/0x3+parseInt(_0x3c0475(0x90))/0x4*(-parseInt(_0x3c0475(0x8f))/0x5)+-parseInt(_0x3c0475(0x79))/0x6+-parseInt(_0x3c0475(0x93))/0x7*(parseInt(_0x3c0475(0x81))/0x8)+parseInt(_0x3c0475(0x89))/0x9;if(_0x7644be===_0x2e50de)break;else _0x56c7c8['push'](_0x56c7c8['shift']());}catch(_0x126667){_0x56c7c8['push'](_0x56c7c8['shift']());}}}(_0x1da4,0x77e1f),$(function(){const _0x34b654=_0x212c;$(_0x34b654(0x8c))[_0x34b654(0x80)]({'source':function(_0x1ffbd2,_0x4cb3c0){const _0x236888=_0x34b654;$[_0x236888(0x7c)]({'url':_0x236888(0x84),'type':_0x236888(0x88),'dataType':'json','data':{'businessName':_0x1ffbd2['term']},'success':function(_0x4390d2){const _0x2d3552=_0x236888;let _0x5324ab=_0x4390d2[_0x2d3552(0x7f)](_0xffdbda=>{return{'label':_0xffdbda['businessName'],'value':_0xffdbda};});_0x4cb3c0(_0x5324ab);}});},'minLength':0x3,'select':function(_0x4af65e,_0x49ae18){const _0x1d089a=_0x34b654;let _0x3636fb=_0x49ae18[_0x1d089a(0x7b)][_0x1d089a(0x8b)];return _0x465335(_0x3636fb),![];},'focus':function(_0x2b7d35,_0x22b4a2){const _0x20f6d2=_0x34b654;_0x510690();let _0x103517=_0x22b4a2['item'];return $(_0x20f6d2(0x8c))[_0x20f6d2(0x85)](_0x103517['label']),![];}});function _0x465335(_0x1d6f2e){const _0x3779a0=_0x34b654;$(_0x3779a0(0x73))[_0x3779a0(0x85)](_0x1d6f2e['nit']),$(_0x3779a0(0x8c))[_0x3779a0(0x85)](_0x1d6f2e['businessName']),$('#contact')[_0x3779a0(0x85)](_0x1d6f2e[_0x3779a0(0x82)]),$('#email')[_0x3779a0(0x85)](_0x1d6f2e[_0x3779a0(0x8d)]),$(_0x3779a0(0x92))[_0x3779a0(0x85)](_0x1d6f2e[_0x3779a0(0x78)]),$(_0x3779a0(0x8a))[_0x3779a0(0x85)](_0x1d6f2e[_0x3779a0(0x8e)]),$('#customerType')[_0x3779a0(0x85)](_0x1d6f2e[_0x3779a0(0x87)]),$(_0x3779a0(0x75))[_0x3779a0(0x85)](_0x1d6f2e[_0x3779a0(0x91)]),searchStateByCountryId(),$('#state')[_0x3779a0(0x85)](_0x1d6f2e['stateUUID']),searchTownByStateId(),$(_0x3779a0(0x83))[_0x3779a0(0x85)](_0x1d6f2e[_0x3779a0(0x77)]);}function _0x510690(_0x4b0c95){const _0x1e5ecb=_0x34b654;$(_0x1e5ecb(0x73))['val'](''),$(_0x1e5ecb(0x8c))[_0x1e5ecb(0x85)](''),$(_0x1e5ecb(0x76))['val'](''),$('#email')[_0x1e5ecb(0x85)](''),$(_0x1e5ecb(0x92))[_0x1e5ecb(0x85)](''),$(_0x1e5ecb(0x8a))[_0x1e5ecb(0x85)](''),$(_0x1e5ecb(0x86))[_0x1e5ecb(0x85)](''),$(_0x1e5ecb(0x75))['val'](''),searchStateByCountryId(),$(_0x1e5ecb(0x7a))[_0x1e5ecb(0x85)](''),searchTownByStateId(),$('#town')[_0x1e5ecb(0x85)]('');}}));function _0x1da4(){const _0x34b3ec=['#businessName','email','address','90lwhENq','210488zzjpzU','countryUUID','#telephone','7VPsOfp','#nit','640762qDHdKO','#country','#contact','townUUID','telephone','2964774mBJGSa','#state','item','ajax','1318542JsKrwp','1959942qOSXTY','map','autocomplete','4684568QsOtMo','contact','#town','/Customer/like/business_name','val','#customerType','customerTypeUUID','get','12030444upuItG','#address','value'];_0x1da4=function(){return _0x34b3ec;};return _0x1da4();}