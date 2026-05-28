async function genererBillet() {

    const nom = document.getElementById("nom").value;

    if (!nom) {
        alert("Veuillez entrer un nom");
        return;
    }

    const response = await fetch(
        "http://localhost:8080/api/generate?nom="
        + encodeURIComponent(nom),
        {
            method: "POST"
        }
    );

    const blob = await response.blob();

    const imageUrl = URL.createObjectURL(blob);

    const preview = document.getElementById("preview");

    preview.src = imageUrl;

    preview.style.display = "block";

    const download = document.getElementById("download");

    download.href = imageUrl;

    download.style.display = "inline-block";
}