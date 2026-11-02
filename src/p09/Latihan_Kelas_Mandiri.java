package p09;

import java.awt.*;
import javax.swing.*;

public class Latihan_Kelas_Mandiri {
    // Langkah 5: Batas maksimum stok yang disepakati
    private static final int BATAS_MAKS_STOK = 1_000_000;

    static void tampil() {
        JFrame f = new JFrame("Form Inventori - Modul 09");
        CardLayout layout = new CardLayout();
        JPanel kartu = new JPanel(layout);

        // --- PANEL 1: FORM INPUT ---
        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JTextField nama = new JTextField(16);
        JTextField stok = new JTextField(16);
        JButton tinjau = new JButton("Tinjau");
        JButton bersihkan = new JButton("Bersihkan"); // Langkah 6

        form.add(new JLabel("Nama Barang:"));
        form.add(nama);
        form.add(new JLabel("Stok Awal:"));
        form.add(stok);
        form.add(new JLabel("Aksi:"));
        form.add(tinjau);
        form.add(new JLabel("Reset:"));
        form.add(bersihkan);

        // --- PANEL 2: RINGKASAN DATA ---
        JPanel ringkas = new JPanel(new BorderLayout(10, 10));
        ringkas.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel hasil = new JLabel("", SwingConstants.CENTER);
        JButton kembali = new JButton("Kembali");

        ringkas.add(new JLabel("Ringkasan Data Barang", SwingConstants.CENTER), BorderLayout.NORTH);
        ringkas.add(hasil, BorderLayout.CENTER);
        ringkas.add(kembali, BorderLayout.SOUTH);

        // Pendaftaran kartu ke CardLayout
        kartu.add(form, "form");
        kartu.add(ringkas, "ringkas");

        // --- EVENT HANDLER: TOMBOL TINJAU ---
        tinjau.addActionListener(e -> {
            try {
                String teksNama = nama.getText().trim();
                String teksStok = stok.getText().trim();

                // 1. Validasi Field Kosong
                if (teksNama.isBlank()) {
                    throw new IllegalArgumentException("Nama barang wajib diisi!");
                }
                if (teksStok.isBlank()) {
                    throw new IllegalArgumentException("Stok barang wajib diisi!");
                }

                // 2. Konversi Tipe Data
                int nilai = Integer.parseInt(teksStok);

                // 3. Validasi Rentang (Batas Minimum & Maksimum)
                if (nilai < 0 || nilai > BATAS_MAKS_STOK) {
                    throw new IllegalArgumentException("Stok harus berupa angka antara 0 sampai " + BATAS_MAKS_STOK);
                }

                // 4. Tampilkan Ringkasan & Pindah Tampilan jika Valid
                hasil.setText("<html><div style='text-align: center;'>"
                        + "<b>Nama Barang:</b> " + teksNama + "<br>"
                        + "<b>Stok Awal:</b> " + nilai
                        + "</div></html>");
                layout.show(kartu, "ringkas");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(f,
                        "Stok harus berupa bilangan bulat positif yang valid!",
                        "Kesalahan Format Input",
                        JOptionPane.ERROR_MESSAGE);
                stok.requestFocusInWindow();
                stok.selectAll();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(f,
                        ex.getMessage(),
                        "Kesalahan Validasi",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // --- EVENT HANDLER: TOMBOL KEMBALI ---
        kembali.addActionListener(e -> {
            layout.show(kartu, "form");
            nama.requestFocusInWindow();
        });

        // --- EVENT HANDLER: TOMBOL BERSIHKAN (Langkah 6) ---
        bersihkan.addActionListener(e -> {
            nama.setText("");
            stok.setText("");
            nama.requestFocusInWindow();
        });

        f.add(kartu);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::tampil);
    }
}