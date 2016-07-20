/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.igov.analytic.model.process;

import org.apache.log4j.Logger;
import org.igov.model.core.GenericEntityDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author olga
 */
@Repository
public class ProcessDaoImpl extends GenericEntityDao<Long, Process> implements ProcessDao {

    private static final Logger log = Logger.getLogger(ProcessDaoImpl.class);

    protected ProcessDaoImpl() {
        super(Process.class);
    }
}
