package com.winston.jornada.controller.jsf.segusuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

import com.powerlogic.jcompany.commons.PlcBaseContextVO;
import com.powerlogic.jcompany.commons.PlcException;
import com.powerlogic.jcompany.commons.config.qualifiers.QPlcSpecific;
import com.powerlogic.jcompany.config.collaboration.FormPattern;
import com.powerlogic.jcompany.controller.jsf.PlcBaseSaveMB;
import com.winston.jornada.entity.seguranca.SegUsuario;

@QPlcSpecific(name="segusuario")
public class SegUsuarioBaseSave extends PlcBaseSaveMB {

	private static final long serialVersionUID = 229403406159405533L;

	@Override
	protected boolean saveBefore(FormPattern pattern, Object entityPlc, PlcBaseContextVO context) {
		validarSenha(entityPlc);
		
		return super.saveBefore(pattern, entityPlc, context);
	}

	private void validarSenha(Object entityPlc) throws PlcException {
		SegUsuario usuario = (SegUsuario) entityPlc;
		
		if (StringUtils.isNotEmpty(usuario.getSenhaAux()) && StringUtils.isEmpty(usuario.getSenhaAuxConfirmacao())) {
			throw new PlcException("{seguranca.usuario.informar.ambas.senhas}");
		}

		if (StringUtils.isEmpty(usuario.getSenhaAux()) && StringUtils.isNotEmpty(usuario.getSenhaAuxConfirmacao())) {
			throw new PlcException("{seguranca.usuario.informar.ambas.senhas}");
		}
		
		if (StringUtils.isNotEmpty(usuario.getSenhaAux()) && StringUtils.isNotEmpty(usuario.getSenhaAuxConfirmacao())) {
			
			if (!usuario.getSenhaAux().equals(usuario.getSenhaAuxConfirmacao())) {
				throw new PlcException("{seguranca.usuario.senhas.nao.conferem}");
			} else {
			
				try {
					MessageDigest md = MessageDigest.getInstance("SHA1");

					md.update(usuario.getSenhaAux().getBytes());
					byte[] hash = md.digest();

					String senha = bytesToHex(hash);

					usuario.setSenha(senha);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String bytesToHex(byte[] b) {
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buf = new StringBuffer();
		
		for (int j = 0; j < b.length; j++) {
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		
		return buf.toString();
	}
	
}
