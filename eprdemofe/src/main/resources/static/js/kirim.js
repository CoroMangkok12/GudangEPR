document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('logoutButton').addEventListener('click', function() {
        window.location.href = 'http://localhost:9009/login';
    });

    document.getElementById('cetakButton').addEventListener('click', generatePDF);
});

function generatePDF() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    const kop = [
        { text: "PT. Enggal Prima Raya", fontSize: 14, bold: true },
        { text: "Surat Pengiriman Barang", fontSize: 12 },
        { text: `Tanggal: ${new Date().toLocaleDateString()}`, fontSize: 10 }
    ];

    kop.forEach((line, index) => {
        doc.setFontSize(line.fontSize);
        if (line.bold) {
            doc.setFont("helvetica", "bold");
        } else {
            doc.setFont("helvetica", "normal");
        }
        doc.text(line.text, 14, 10 + (index * 10));
    });

    const selectedItems = Array.from(document.querySelectorAll('.barangCheckbox:checked'))
        .map(checkbox => {
            const row = checkbox.closest('tr');
            const code = row.cells[0].innerText;
            const nama = row.cells[1].innerText;
            const qtyAvailable = parseInt(row.cells[2].innerText, 10);
            const qtyToSend = parseInt(row.querySelector('.qtyInput').value, 10);

            if (qtyToSend > qtyAvailable || qtyToSend <= 0) {
                throw new Error(`Invalid quantity for item: ${nama}`);
            }

            // Update the quantity available
            row.cells[2].innerText = qtyAvailable - qtyToSend;

            return { code, nama, qty: qtyToSend };
        });

    if (selectedItems.length === 0) {
        alert("No items selected");
        return;
    }

    const tableColumn = ["Code", "Nama", "Qty"];
    const tableRows = selectedItems.map(item => [item.code, item.nama, item.qty]);

    doc.autoTable({
        startY: 40,
        head: [tableColumn],
        body: tableRows,
        styles: { fontSize: 10 }
    });

    const today = new Date();
    const dateString = today.toISOString().split('T')[0];
    doc.save(`SPB ${dateString}.pdf`);
}