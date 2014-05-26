
	function Msnry(){
		var container = document.querySelector('.pro_body');
		var msnry;
		imagesLoaded( container, function() {
		msnry = new Masonry( container,{
			columnWidth: 435,
			itemSelector: '.post',
			gutter:20
			});
		});
	}
	Msnry();
