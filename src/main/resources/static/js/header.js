function searchClick() {
    window.location.replace("/shop?search=" + encodeURI($("input[name=search-input]").val()));
}