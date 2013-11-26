function checkAll(selector) {
	$(selector + " input:checkbox").attr('checked', 'checked');
	return false;
}

function uncheckAll(selector) {
	$(selector + " input:checkbox").attr('checked', false);
	return false;
}

var defaultMaskPatterns = {
	"cpf"	: "###.###.###-##",
	"cnpj"	: "##.###.###/####-##",
	"rg"	: "##.###.###-#",
	"pis"	: "##########-#",
	"cei"	: "############",
	"cep"	: "##.###-###",
	"phone" : "(##) ####-####"
}

function maskIdentity(identityInputId, initialIdentityType, identityTypeInputId) {
	var identityTypeMask = new InputMask(defaultMaskPatterns[initialIdentityType], identityInputId);
	
	if (identityTypeInputId) {
		$("#" + identityTypeInputId).change(function() {
			var newIdentityType = $(this).val();
			toggleIdentityType(identityInputId, newIdentityType, identityTypeMask);
		});
	}
}

function toggleIdentityType(identityTypeId, identityType, identityTypeMask) {
	$("#" + identityTypeId).val("");
	identityTypeMask.fields = maskBuilder.parse(defaultMaskPatterns[identityType]);
	identityTypeMask.update();	
}

function goToByScroll(id, speed){
	if (speed) {
		speed = 'slow';
	}
	
	$('html,body').animate({scrollTop: $(id).offset().top}, speed);
}