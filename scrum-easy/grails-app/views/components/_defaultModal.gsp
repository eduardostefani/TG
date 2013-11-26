<div id="defaultModal" class="modal fade">
	<div class="modal-dialog">
      <div class="modal-content">
		<div class="modal-header">
			<a href="#" class="close">&times;</a>
			<h3></h3>
		</div>
		<div class="modal-body">
			<p></p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn btn-danger"><g:message code="default.button.delete.label" /></a>
			<a href="#" class="btn btn-default secondary"><g:message code="default.button.cancel.label" /></a>
		</div>
	   </div>
	</div>
</div>

<r:script>
$("#defaultModal div.modal-footer a.secondary").click(function() {
	$("#defaultModal").modal('hide');
});

$("#defaultModal div.modal-header a.close").click(function() {
	$("#defaultModal").modal('hide');
});

$("a[data-controls-modal=defaultModal]").each(function() {
	var btn = $(this);
	btn.click(function() {
		$("#defaultModal div.modal-header h3").html(btn.data('header'));
		$("#defaultModal div.modal-body p").html(btn.data('message'));
		$("#defaultModal div.modal-footer a.btn-danger").html(btn.data('label'));
		$("#defaultModal div.modal-footer a.btn-danger").attr('href', btn.data('url'));
	});
});
</r:script>