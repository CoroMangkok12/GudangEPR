$(document).ready(function() {
    loadBarangMasuk();

    $('#barangmasuk-form').on('submit', function(event) {
        event.preventDefault();
        const formData = {
            code: $('#code').val(),
            nama: $('#nama').val(),
            qty: $('#qty').val(),
            tanggal: $('#tanggal').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/barangmasuk',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function(response) {
                $('#barangMasukModal').modal('hide');
                loadBarangMasuk();
            }
        });
    });

    $('#update-barangmasuk-form').on('submit', function(event) {
        event.preventDefault();
        const id = $('#update-barangmasuk-form').data('id');
        const formData = {
            no: id,
            code: $('#update-code').val(),
            nama: $('#update-nama').val(),
            qty: $('#update-qty').val(),
            tanggal: $('#update-tanggal').val()
        };
        $.ajax({
            type: 'PUT',
            url: '/api/barangmasuk',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function(response) {
                $('#updateBarangMasukModal').modal('hide');
                loadBarangMasuk();
            }
        });
    });

    $('#searchBar').on('keyup', function() {
        const searchTerm = $(this).val().toLowerCase();
        filterTable(searchTerm);
    });

    function loadBarangMasuk() {
        $.ajax({
            type: 'GET',
            url: '/api/barangmasuk',
            success: function(response) {
                let rows = '';
                response.forEach(item => {
                    rows += `
                        <tr>
                            <td>${item.no}</td>
                            <td>${item.code}</td>
                            <td>${item.nama}</td>
                            <td>${item.qty}</td>
                            <td>${item.tanggal}</td>
                            <td>
                                <button class="btn btn-warning btn-sm" onclick="editBarangMasuk(${item.no})">Update</button>
                                <button class="btn btn-danger btn-sm" onclick="deleteBarangMasuk(${item.no})">Delete</button>
                            </td>
                        </tr>
                    `;
                });
                $('#barangMasukTable tbody').html(rows);
            }
        });
    }

    function filterTable(searchTerm) {
        $('#barangMasukTable tbody tr').each(function() {
            const rowText = $(this).text().toLowerCase();
            if (rowText.indexOf(searchTerm) !== -1) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    }

    window.editBarangMasuk = function(no) {
        $.ajax({
            type: 'GET',
            url: '/api/barangmasuk/' + no,
            success: function(response) {
                $('#update-barangmasuk-form').data('id', no);
                $('#update-code').val(response.code);
                $('#update-nama').val(response.nama);
                $('#update-qty').val(response.qty);
                $('#update-tanggal').val(response.tanggal);
                $('#updateBarangMasukModal').modal('show');
            }
        });
    };

    window.deleteBarangMasuk = function(no) {
        if (confirm('Are you sure you want to delete this item?')) {
            $.ajax({
                type: 'DELETE',
                url: '/api/barangmasuk/' + no,
                success: function(response) {
                    loadBarangMasuk();
                }
            });
        }
    };
});

document.addEventListener('DOMContentLoaded', function() {
    // Logout button event listener
    document.getElementById('logoutButton').addEventListener('click', function() {
        window.location.href = 'http://localhost:9009/login';
    });

    const rows = document.querySelectorAll('#barangMasukTable tbody tr');
    rows.forEach(row => {
        const code = row.querySelector('td:nth-child(2)').textContent;
        const nama = row.querySelector('td:nth-child(3)').textContent;
        const qty = row.querySelector('td:nth-child(4)').textContent;
        const tanggal = row.querySelector('td:nth-child(5)').textContent;
        

        const updateButton = document.createElement('button');
        updateButton.className = 'btn btn-link btn-sm mr-2 p-0';
        updateButton.innerHTML = '<img src="/img/update-icon.png" alt="Update" width="24" height="24">';
        updateButton.setAttribute('data-toggle', 'modal');
        updateButton.setAttribute('data-target', '#updateBarangMasukModal');
        updateButton.setAttribute('data-toggle', 'tooltip');
        updateButton.setAttribute('title', 'Update');
        updateButton.onclick = function() {
            document.getElementById('update-code').value = code;
            document.getElementById('update-nama').value = nama;
            document.getElementById('update-qty').value = qty;
            document.getElementById('update-tanggal').value = tanggal;
        };

        const deleteButton = document.createElement('button');
        deleteButton.className = 'btn btn-link btn-sm p-0';
        deleteButton.innerHTML = '<img src="/img/delete-icon.png" alt="Delete" width="24" height="24">';
        deleteButton.setAttribute('data-toggle', 'tooltip');
        deleteButton.setAttribute('title', 'Delete');
        deleteButton.onclick = function() {
            if (confirm('Are you sure you want to delete this item?')) {
                row.remove();
            }
        };

        const actionsCell = row.querySelector('td:last-child');
        actionsCell.innerHTML = ''; // Clear existing content
        actionsCell.appendChild(updateButton);
        actionsCell.appendChild(deleteButton);
    });

    $('[data-toggle="tooltip"]').tooltip();

    document.getElementById('printButton').addEventListener('click', function() {
        const { jsPDF } = window.jspdf;
        const doc = new jsPDF({
            orientation: 'landscape'
        });

        // Add a title
        doc.setFontSize(16);
        doc.text("Stok Gudang EPR", doc.internal.pageSize.getWidth() / 2, 15, { align: 'center' });

        // Add the current date
        const currentDate = new Date().toLocaleDateString();
        doc.setFontSize(12);
        doc.text(`Tanggal Cetak: ${currentDate}`, 10, 25);

        // Get table data
        const table = document.getElementById('barangMasukTable');
        const headers = Array.from(table.querySelectorAll('thead th')).map(th => th.textContent.trim());
        const rows = Array.from(table.querySelectorAll('tbody tr'));

        // Set initial position and dimensions
        let yPos = 35;
        const xPos = 10;
        const pageWidth = doc.internal.pageSize.getWidth();
        const pageHeight = doc.internal.pageSize.getHeight();
        const margin = 10;
        const usablePageWidth = pageWidth - 2 * margin;
        const colWidth = usablePageWidth / (headers.length - 1); // Exclude "Actions" column

        // Function to draw cell borders
        function drawCell(x, y, w, h) {
            doc.rect(x, y, w, h);
        }

        // Function to get wrapped cell height
        function getWrappedCellHeight(text, width) {
            const lines = doc.splitTextToSize(text, width - 4); // 4 is for left and right padding
            return lines.length * 7; // Assuming 7 points per line
        }

        // Print headers
        doc.setFontSize(10);
        doc.setFont(undefined, 'bold');
        headers.forEach((header, index) => {
            if (index !== headers.length - 1) { // Skip the "Actions" column
                const cellHeight = getWrappedCellHeight(header, colWidth);
                drawCell(xPos + index * colWidth, yPos, colWidth, cellHeight);
                doc.text(header, xPos + index * colWidth + 2, yPos + 5, { maxWidth: colWidth - 4 });
            }
        });

        yPos += getWrappedCellHeight(headers[0], colWidth) + 2; // Move to next row

        // Print rows
        doc.setFont(undefined, 'normal');
        rows.forEach((row, rowIndex) => {
            const cells = Array.from(row.querySelectorAll('td'));
            let maxCellHeight = 0;

            // Calculate max cell height for the row
            cells.forEach((cell, index) => {
                if (index !== cells.length - 1) { // Skip the "Actions" column
                    const cellHeight = getWrappedCellHeight(cell.textContent.trim(), colWidth);
                    maxCellHeight = Math.max(maxCellHeight, cellHeight);
                }
            });

            // Check if we need a new page
            if (yPos + maxCellHeight >= pageHeight - margin) {
                doc.addPage();
                yPos = margin;
            }

            cells.forEach((cell, index) => {
                if (index !== cells.length - 1) { // Skip the "Actions" column
                    drawCell(xPos + index * colWidth, yPos, colWidth, maxCellHeight);
                    doc.text(cell.textContent.trim(), xPos + index * colWidth + 2, yPos + 5, { maxWidth: colWidth - 4 });
                }
            });

            yPos += maxCellHeight + 2; // Move to next row
        });

        // Generate filename with current date
        const saveDate = new Date();
        const formattedDate = saveDate.toISOString().split('T')[0]; // Format: YYYY-MM-DD
        const filename = `Gudang ${formattedDate}.pdf`;

        // Save the PDF with the new filename
        doc.save(filename);
    });
});
