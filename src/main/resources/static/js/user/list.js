function clickBtnDelete(){

    var idck;

    // frmフォーム内の部品idckを指定
    if (typeof document.frm.idck.length === 'undefined'){
        idck = [{ 'cheked' : document.frm.idck.checked }];
    } else {
        idck = document.frm.idck;
    }

    var count = 0;
    for (i = 0; i < idck.length; i++){
        if (idck[i].checked) {
            count++;
        }
    }

    // Userが選択されていなければ処理を中止
    if (count == 0){
        alert('Userが選択されていません。');
        return false;
    }

    if (window.confirm(`${count}件削除して良いですか？`)){
        return true;
    } else {
        return false;
    }

}

// 削除ボタンに関数を割り当てる(onclickでクリック時に実行される)
document.getElementById("deleteRun").onclick = clickBtnDelete;