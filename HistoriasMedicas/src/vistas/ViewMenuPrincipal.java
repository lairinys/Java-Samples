
package vistas;

import javax.swing.JOptionPane;

/**
 *
 * @author lairinyssilva
 */
public class ViewMenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    public ViewMenuPrincipal() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gestionarHistorial = new javax.swing.JButton();
        gestionarPacientes = new javax.swing.JButton();
        gestionarMedicos = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Historias Clínicas");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        gestionarHistorial.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        gestionarHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/008-notepad.png"))); // NOI18N
        gestionarHistorial.setText("Gestionar Historial");
        gestionarHistorial.setAlignmentY(0.0F);
        gestionarHistorial.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        gestionarHistorial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gestionarHistorial.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gestionarHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionarHistorialActionPerformed(evt);
            }
        });

        gestionarPacientes.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        gestionarPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/006-bed.png"))); // NOI18N
        gestionarPacientes.setText("Gestionar Pacientes");
        gestionarPacientes.setAlignmentY(0.0F);
        gestionarPacientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        gestionarPacientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gestionarPacientes.setPreferredSize(new java.awt.Dimension(318, 80));
        gestionarPacientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gestionarPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionarPacientesActionPerformed(evt);
            }
        });

        gestionarMedicos.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        gestionarMedicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/005-doctor.png"))); // NOI18N
        gestionarMedicos.setText("Gestionar Médicos");
        gestionarMedicos.setAlignmentY(0.0F);
        gestionarMedicos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        gestionarMedicos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gestionarMedicos.setPreferredSize(new java.awt.Dimension(318, 80));
        gestionarMedicos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gestionarMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionarMedicosActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        salir.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/013-exit-1.png"))); // NOI18N
        salir.setBorderPainted(false);
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Menú Principal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salir)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(salir)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(gestionarPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(gestionarMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(gestionarHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gestionarPacientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gestionarMedicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gestionarHistorial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        setBounds(0, 0, 624, 300);
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        cerrar();
    }//GEN-LAST:event_salirActionPerformed

    private void gestionarPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionarPacientesActionPerformed
     
        ViewMenuSecundario menuPacientes=new ViewMenuSecundario();
        abrirMenu(menuPacientes,"Pacientes");
        
    }//GEN-LAST:event_gestionarPacientesActionPerformed

    private void gestionarHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionarHistorialActionPerformed
        
        ViewMenuSecundario menuHistorial=new ViewMenuSecundario();
        abrirMenu(menuHistorial,"Historial");
    }//GEN-LAST:event_gestionarHistorialActionPerformed

    private void gestionarMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionarMedicosActionPerformed
        
        ViewMenuSecundario menuMedicos=new ViewMenuSecundario();
        abrirMenu(menuMedicos,"Médicos");
    }//GEN-LAST:event_gestionarMedicosActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ViewMenuPrincipal().setVisible(true);
        });
    }
    
    private void abrirMenu(ViewMenuSecundario secundario,String tipo){
      
      secundario.configurar(secundario,tipo); 
     
      this.gestionarHistorial.setEnabled(false);
      this.gestionarMedicos.setEnabled(false);
      this.gestionarPacientes.setEnabled(false);
      
      secundario.setVisible (true);  
      
    }
    
    public void regresar(){
        this.setGestionarHistorialEnabled(true);
        this.setGestionarMedicosEnabled(true);
        this.setGestionarPacientesEnabled(true);
    }
    private void cerrar(){
        Object [] opciones ={"Si","No"};
        int eleccion = JOptionPane.showOptionDialog(rootPane,"En realidad desea realizar cerrar la aplicacion","Mensaje de Confirmacion",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,null,opciones,"Si");
        if (eleccion == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

    public void setGestionarHistorialEnabled(boolean valor) {
        this.gestionarHistorial.setEnabled(valor);
    }

    public void setGestionarMedicosEnabled(boolean valor) {
        this.gestionarMedicos.setEnabled(valor);
    }

    public void setGestionarPacientesEnabled(boolean valor) {
        this.gestionarPacientes.setEnabled(valor);
    }
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton gestionarHistorial;
    private javax.swing.JButton gestionarMedicos;
    private javax.swing.JButton gestionarPacientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
